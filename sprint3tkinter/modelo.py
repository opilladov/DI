import threading
import time
import random
from recursos import descargar_imagen

class GameModel:
    def __init__(self, difficulty, player_name, cell_size=100):
        self.difficulty = difficulty
        self.player_name = player_name
        self.cell_size = cell_size
        self.board = self._generate_board()
        self.hidden_image = None
        self.images_loaded = threading.Event()
        self.start_time = None
        self.moves = 0
        self.pairs_found = 0
        self.image_urls = self._get_image_urls()

        threading.Thread(target=self._load_images).start()

    def _generate_board(self):
        num_pairs = {"facil": 8, "medio": 18, "dificil": 32}[self.difficulty]
        images = list(range(num_pairs)) * 2
        random.shuffle(images)
        return images

    def _get_image_urls(self):
        base_url = "https://raw.githubusercontent.com/opilladov/DI/refs/heads/main/sprint3tkinter/"
        return [f"{base_url}{i}.jpg" for i in range(max(self.board) + 1)]

    def _load_images(self):
        self.loaded_images = {}
        for i, image_id in enumerate(set(self.board)):
            image = descargar_imagen(self.image_urls[image_id], (self.cell_size, self.cell_size))
            if image:
                self.loaded_images[image_id] = image
        self.images_loaded.set()

    def images_are_loaded(self):
        return self.images_loaded.is_set()

    def start_timer(self):
        self.start_time = time.time()

    def get_time(self):
        return int(time.time() - self.start_time)

    def check_match(self, pos1, pos2):
        self.moves += 1
        if self.board[pos1] == self.board[pos2]:
            self.pairs_found += 1
            return True
        return False

    def is_game_complete(self):
        return self.pairs_found == len(self.board) // 2

    def save_score(self):
        try:
            with open("ranking.txt", "a") as file:
                file.write(f"{self.player_name},{self.difficulty},{self.moves},{self.get_time()}\n")
        except IOError as e:
            print(f"Error al guardar la puntuación: {e}")

    def load_scores(self):
        scores = {"facil": [], "medio": [], "dificil": []}
        try:
            with open("ranking.txt", "r") as file:
                for line in file:
                    name, difficulty, moves, time_taken = line.strip().split(",")
                    scores[difficulty].append({
                        "nombre": name,
                        "movimientos": int(moves),
                        "tiempo": int(time_taken)
                    })
            for key in scores:
                scores[key] = sorted(scores[key], key=lambda x: x['movimientos'])[:10]
        except IOError as e:
            print(f"Error al cargar las puntuaciones: {e}")
        return scores