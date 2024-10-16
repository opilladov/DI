import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de radiobutton")
ventana.geometry("300x200")

def cambiar_color():
    colores = color.get()
    ventana.configure(bg=colores)

color = tk.StringVar()
color.set("Blanco")

radio1 = tk.Radiobutton(ventana, text="Rojo", variable=color, value="red", command=cambiar_color)
radio1.pack()

radio2 = tk.Radiobutton(ventana, text="Azul", variable=color, value="blue", command=cambiar_color)
radio2.pack()

radio3 = tk.Radiobutton(ventana, text="Verde", variable=color, value="green", command=cambiar_color)
radio3.pack()

ventana.mainloop()