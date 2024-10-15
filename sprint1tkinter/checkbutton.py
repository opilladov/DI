import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de checkbutton")
ventana.geometry("300x200")

def mostrar_seleccion():
    aficiones =""
    if leer.get():
        aficiones = aficiones + " leer"
    if deporte.get():
        aficiones = aficiones + " deporte"
    if musica.get():
        aficiones = aficiones + " musica"
    etiqueta.config(text=f"Seleccionado: {aficiones}")

leer = tk.IntVar()
deporte = tk.IntVar()
musica = tk.IntVar()

check1 = tk.Checkbutton(ventana, text="Leer", variable=leer, command=mostrar_seleccion)
check1.pack()

check2 = tk.Checkbutton(ventana, text="Deporte", variable=deporte, command=mostrar_seleccion)
check2.pack()

check3 = tk.Checkbutton(ventana, text="Musica", variable=musica, command=mostrar_seleccion)
check3.pack()

etiqueta = tk.Label(ventana, text="Seleccionado: Nada seleccionado")
etiqueta.pack()

ventana.mainloop()