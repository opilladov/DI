import tkinter as tk

ventana = tk.Tk()
ventana.title("Ventana de frame") 
ventana.geometry("600x600")

# Frame inferior
frame_superior = tk.Frame(ventana)
frame_superior.pack()

label1 = tk.Label(frame_superior, text="Escribe algo")
label1.pack()

label2 = tk.Label(frame_superior, text="para tener contenido:")
label2.pack()

entry = tk.Entry(frame_superior)
entry.pack()

# Frame inferior
frame_inferior = tk.Frame(ventana)
frame_inferior.pack()

def mostrar():
    resultado = entry.get()
    label_resultado.config(text=f"Resultado: {resultado}")

def borrar():
    entry.delete(0, tk.END)
    label_resultado.config(text="")

boton_mostrar = tk.Button(frame_inferior, text="Mostrar", command=mostrar)
boton_mostrar.pack()

boton_borrar = tk.Button(frame_inferior, text="Borrar", command=borrar)
boton_borrar.pack()

label_resultado = tk.Label(frame_inferior, text="")
label_resultado.pack()

ventana.mainloop()