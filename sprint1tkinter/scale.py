import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de scale")
ventana.geometry("600x600")

def actualizar(valor):
    etiqueta.config(text=f"Valor: {valor}")

scale = tk.Scale(ventana, from_=0, to=100, orient='horizontal', command=actualizar)
scale.pack()

etiqueta = tk.Label(ventana, text="Valor: 0")
etiqueta.pack()

ventana.mainloop()