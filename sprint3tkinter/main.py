import tkinter as tk
from controlador import GameController
from modelo import GameModel

if __name__ == "__main__":
    root = tk.Tk()
    root.title("Juego de Memoria")

    model = GameModel(difficulty="medio", player_name="Jugador")

    controller = GameController(root, model)

    root.mainloop()