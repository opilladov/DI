import tkinter as tk
from modelo import Modelo
from vista import Vista
from controlador import Controlador

if __name__ == "__main__":
    root = tk.Tk()
    modelo = Modelo()
    vista = Vista(root)
    controlador = Controlador(modelo, vista)
    root.mainloop()