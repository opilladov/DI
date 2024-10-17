import tkinter as tk
from tkinter import messagebox

ventana = tk.Tk()
ventana.title("Aplicación de Registro de Usuarios")
ventana.geometry("600x600")

etiqueta1 = tk.Label(ventana, text="Nombre:")
etiqueta1.grid(row=0, column=0)

entry_nombre = tk.Entry(ventana)
entry_nombre.grid(row=0, column=1)

etiqueta2 = tk.Label(ventana, text="Edad:")
etiqueta2.grid(row=1, column=0)

scale = tk.Scale(ventana, from_=0, to=100, orient='horizontal')
scale.grid(row=1, column=1)

etiqueta3 = tk.Label(ventana, text="Genero:")
etiqueta3.grid(row=2, column=0)

sexo = tk.StringVar()
sexo.set(None)

radio1 = tk.Radiobutton(ventana, text="Masculino", variable=sexo, value="masculino")
radio1.grid(row=2, column=1)

radio2 = tk.Radiobutton(ventana, text="Femenino",variable=sexo, value="femenino")
radio2.grid(row=3, column=1)

radio3 = tk.Radiobutton(ventana, text="Otro", variable=sexo, value="otro")
radio3.grid(row=4, column=1)

def anadir():
    nombre = entry_nombre.get()
    edad = scale.get()
    genero = sexo.get()

    if not nombre or genero == "":
        messagebox.showwarning("AVISO", "Completa los campos!!!")
        return

    usuario = f"{nombre}, Edad: {edad}, Género: {genero}"
    listbox.insert(tk.END, usuario)

boton1 = tk.Button(ventana, text="Añadir", command=anadir)
boton1.grid(row=5, column=1)

frame = tk.Frame(ventana)
frame.grid(row=6, column=0)

tk.Label(frame, text="Usuarios:").grid(row=8, column=0)
listbox = tk.Listbox(frame)
listbox.grid(row=8, column=1)

scrollbar = tk.Scrollbar(frame,command=listbox.yview)
scrollbar.grid(row=8, column=2)

def borrar():
    seleccionado = listbox.curselection()
    if seleccionado:
        listbox.delete(seleccionado)
    else:
        messagebox.showwarning("Advertencia", "Seleccionar un usuario")

borrar = tk.Button(ventana, text="Borrar", command=borrar)
borrar.grid(row=9, column=0)

def salir():
    ventana.destroy()

salir = tk.Button(ventana, text="Salir", command=salir)
salir.grid(row=10, column=0)

menu = tk.Menu(ventana)
ventana.config(menu=menu)

def guardar():
    messagebox.showinfo("Guardar", "La lista ha sido guardada")

def cargar():
    messagebox.showinfo("Cargar", "La lista ha sido cargada")

menu2 = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Lista", menu=menu2)
menu2.add_command(label="Guardar", command=guardar)
menu2.add_separator()
menu2.add_command(label="Cargar", command=cargar)

ventana.mainloop()