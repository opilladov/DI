import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de listbox")
ventana.geometry("500x400")

def mostrar_selecciones():
    seleccion = listbox.curselection()
    elementos = [listbox.get(i) for i in seleccion]
    etiqueta.config(text=f"Selecciones: {', '.join(elementos)}")

opciones = ["Manzana", "Banana", "Naranja"]

listbox = tk.Listbox(ventana, selectmode=tk.MULTIPLE)
for opcion in opciones:
    listbox.insert(tk.END, opcion)
listbox.pack()

boton = tk.Button(ventana, text="Mostrar seleccion", command=mostrar_selecciones)
boton.pack()

etiqueta = tk.Label(ventana, text="Seleccionaste: Ninguno")
etiqueta.pack()

ventana.mainloop()