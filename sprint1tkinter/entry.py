import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de entry")

def saludar():
    nombre = entry_nombre.get()
    saludo = f"¡Hola, {nombre}!"
    label_saludo.config(text=saludo)

label1 = tk.Label(ventana, text="Escribe tu nombre:")
label1.pack()

entry_nombre = tk.Entry(ventana)
entry_nombre.pack()

boton_saludar = tk.Button(ventana, text="Saludar", command=saludar)
boton_saludar.pack()

label_saludo = tk.Label(ventana, text="")
label_saludo.pack()

ventana.mainloop()