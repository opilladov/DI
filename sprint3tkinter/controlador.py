import tkinter as tk
from tkinter import messagebox, simpledialog

from modelo import GameModel
from vista import MainMenu, GameView

class GameController:
    def __init__(self, root, model):
        self.root = root
        self.model = model
        self.selected = []
        self.timer_started = False

        self.main_menu = MainMenu(
            root,
            self.show_difficulty_selection,
            self.show_stats,
            self.root.quit
        )

    def show_difficulty_selection(self):
        difficulty = simpledialog.askstring("Dificultad", "Elige la dificultad: facil, medio, dificil")
        if difficulty in ["facil", "medio", "dificil"]:
            player_name = self.main_menu.ask_player_name()
            if player_name:
                self.start_game(difficulty)

    def start_game(self, difficulty):
        loading_window = self.show_loading_window("Cargando el tablero, por favor espera...")
        self.model = GameModel(difficulty, player_name="Jugador")
        self.check_images_loaded(loading_window)

    def show_loading_window(self, message):
        window = tk.Toplevel(self.root)
        window.title("Cargando...")
        label = tk.Label(window, text=message)
        label.pack(pady=20)
        self.root.update()
        return window

    def check_images_loaded(self, loading_window):
        if self.model.images_are_loaded():
            loading_window.destroy()
            self.game_view = GameView(
                self.root,
                self.on_card_click,
                self.update_move_count,
                self.update_time
            )
            self.game_view.create_board(self.model)
            self.update_time()
        else:
            self.root.after(100, lambda: self.check_images_loaded(loading_window))

    def on_card_click(self, pos):
        if not self.timer_started:
            self.model.start_timer()
            self.timer_started = True
            self.update_time()

        self.selected.append(pos)
        self.game_view.update_board(pos, self.model.board[pos])

        if len(self.selected) == 2:
            self.root.after(1000, self.handle_card_selection)

    def handle_card_selection(self):
        pos1, pos2 = self.selected
        if not self.model.check_match(pos1, pos2):
            self.game_view.reset_cards(pos1, pos2)
        self.selected = []

        self.update_move_count(self.model.moves)
        self.check_game_complete()

    def update_move_count(self, moves):
        self.game_view.update_move_count(moves)

    def check_game_complete(self):
        if self.model.is_game_complete():
            messagebox.showinfo("¡Victoria!", "¡Has completado el juego!")
            self.return_to_main_menu()

    def return_to_main_menu(self):
        self.game_view.destroy()
        self.main_menu = MainMenu(
            self.root,
            self.show_difficulty_selection,
            self.show_stats,
            self.root.quit
        )

    def show_stats(self):
        stats = self.model.load_scores()
        self.main_menu.show_stats(stats)

    def update_time(self):
        if self.timer_started:
            time_elapsed = self.model.get_time()
            self.game_view.update_time(time_elapsed)
            self.root.after(1000, self.update_time)