import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de labels")

label1 = tk.Label(ventana, text="Bienvenid@s")
label1.pack()

label2 = tk.Label(ventana, text="Óscar")
label2.pack()

label3 = tk.Label(ventana, text="Texto inicial")
label3.pack()


def cambiar_texto():
    label3.config(text="Texto cambiado")

boton = tk.Button(ventana, text="Cambiar Texto", command=cambiar_texto)
boton.pack()

ventana.mainloop()