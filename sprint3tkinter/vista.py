import tkinter as tk
from tkinter import Toplevel, simpledialog, messagebox

class GameView:
    def __init__(self, root, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.root = root
        self.window = None
        self.labels = {}
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback
        self.move_count_label = None
        self.time_label = None

    def create_board(self, model):
        self.window = Toplevel(self.root)
        self.window.title("Juego de Memoria")

        rows = cols = int(len(model.board) ** 0.5)
        for i, card_id in enumerate(model.board):
            label = tk.Label(
                self.window,
                text="?",
                width=10,
                height=5,
                relief="raised",
                bg="lightgray"
            )
            label.grid(row=i // cols, column=i % cols)
            label.bind("<Button-1>", lambda e, pos=i: self.on_card_click_callback(pos))
            self.labels[i] = label

        self.move_count_label = tk.Label(self.window, text="Movimientos: 0")
        self.move_count_label.grid(row=rows, column=0, columnspan=cols // 2, sticky="w")

        self.time_label = tk.Label(self.window, text="Tiempo: 0s")
        self.time_label.grid(row=rows, column=cols // 2, columnspan=cols // 2, sticky="e")

    def update_board(self, pos, image):
        if pos in self.labels:
            self.labels[pos].config(image=image)
            self.labels[pos].image = image

    def reset_cards(self, pos1, pos2):
        if pos1 in self.labels and pos2 in self.labels:
            self.labels[pos1].config(text="?", image="", relief="raised")
            self.labels[pos2].config(text="?", image="", relief="raised")

    def update_move_count(self, moves):
        self.move_count_label.config(text=f"Movimientos: {moves}")

    def update_time(self, time_elapsed):
        self.time_label.config(text=f"Tiempo: {time_elapsed}s")

    def destroy(self):
        if self.window:
            self.window.destroy()
            self.labels.clear()

class MainMenu:
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.window = root
        self.window.title("Juego de Memoria")

        tk.Label(self.window, text="Menú Principal", font=("Helvetica", 16)).pack(pady=10)
        tk.Button(self.window, text="Jugar", command=start_game_callback).pack(pady=5)
        tk.Button(self.window, text="Ver Estadísticas", command=show_stats_callback).pack(pady=5)
        tk.Button(self.window, text="Salir", command=quit_callback).pack(pady=5)

    def ask_player_name(self):
        return simpledialog.askstring("Nombre del Jugador", "Introduce tu nombre:")

    def show_stats(self, stats):
        stats_window = Toplevel(self.window)
        stats_window.title("Estadísticas")

        for difficulty, scores in stats.items():
            tk.Label(stats_window, text=f"Dificultad: {difficulty}", font=("Helvetica", 14)).pack()
            for score in scores:
                tk.Label(stats_window,
                         text=f"{score['nombre']} - {score['movimientos']} movimientos, {score['tiempo']}s").pack()