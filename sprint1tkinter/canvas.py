import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de listbox")
ventana.geometry("600x600")

def pintar_rectangulo():
    x1 = coordenada1.get()
    y1 = coordenada2.get()
    x2 = coordenada3.get()
    y2 = coordenada4.get()

    canvas.create_rectangle(x1, y1, x2, y2)

def pintar_circulo():
    x1 = coordenada1.get()
    y1 = coordenada2.get()
    x2 = coordenada3.get()
    y2 = coordenada4.get()

    canvas.create_oval(x1, y1, x2, y2)

label1 = tk.Label(ventana, text="Escribe la coordenada X1:")
label1.pack()

coordenada1 = tk.Entry(ventana)
coordenada1.pack()

label2 = tk.Label(ventana, text="Escribe la coordenada Y1:")
label2.pack()

coordenada2 = tk.Entry(ventana)
coordenada2.pack()

label3 = tk.Label(ventana, text="Escribe la coordenada X2:")
label3.pack()

coordenada3 = tk.Entry(ventana)
coordenada3.pack()

label4 = tk.Label(ventana, text="Escribe la coordenada Y2:")
label4.pack()

coordenada4 = tk.Entry(ventana)
coordenada4.pack()

boton1 = tk.Button(ventana, text="Rectangulo", command=pintar_rectangulo)
boton1.pack()

boton2 = tk.Button(ventana, text="Circulo", command=pintar_circulo)
boton2.pack()

canvas = tk.Canvas(ventana, width=400, height=300, bg="white")
canvas.pack()

ventana.mainloop()