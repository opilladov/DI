import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de buttons")

def aparecer_texto():
    label1 = tk.Label(ventana, text="Bienvenid@s")
    label1.pack()

boton1 = tk.Button(ventana, text="Aparecer texto", command=aparecer_texto)
boton1.pack()

def cerrar_ventana():
    ventana.destroy()

boton2 = tk.Button(ventana, text="Cerrar ventana", command=cerrar_ventana)
boton2.pack()

ventana.mainloop()