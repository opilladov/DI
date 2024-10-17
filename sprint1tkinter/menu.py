import tkinter as tk
from tkinter import messagebox

ventana = tk.Tk()
ventana.title("Ventana de menu") 
ventana.geometry("600x600")

def nueva():
    messagebox.showinfo("Nuevo", "Abrir una nueva ventana")

def salir():
    ventana.destroy()

menu = tk.Menu(ventana)
ventana.config(menu=menu)

archivo = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Archivo", menu=archivo)
archivo.add_command(label="Nuevo", command=nueva)
archivo.add_separator()
archivo.add_command(label="Salir", command=salir)

def ayudar():
    messagebox.showinfo("Ayuda acerca de", "Esto es informacion de ayuda")

ayuda = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Archivo", menu=ayuda)
ayuda.add_command(label="Acerca de", command=ayudar)

ventana.mainloop()