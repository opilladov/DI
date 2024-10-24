import tkinter as tk

class Vista:
    def __init__(self, root):
        self.root = root
        self.root.title("Notas")
        self.root.geometry("500x500")

        self.label_titulo = tk.Label(root, text="Aplicación Notas")
        self.label_titulo.pack()

        self.label_coordenadas = tk.Label(root, text="Coordenadas: (0, 0)")
        self.label_coordenadas.pack()

        self.listbox = tk.Listbox(root)
        self.listbox.pack()

        self.entry_nota = tk.Entry(root)
        self.entry_nota.pack()

        self.boton_agregar = tk.Button(root, text="Agregar Nota")
        self.boton_agregar.pack()

        self.boton_eliminar = tk.Button(root, text="Eliminar Nota")
        self.boton_eliminar.pack()

        self.boton_guardar = tk.Button(root, text="Guardar Notas")
        self.boton_guardar.pack()

        self.boton_cargar = tk.Button(root, text="Cargar Notas")
        self.boton_cargar.pack()

        self.boton_descargar = tk.Button(root, text="Descargar Imagen")
        self.boton_descargar.pack()

        self.label_imagen = tk.Label(root)
        self.label_imagen.pack()

    def actualizar_listbox(self, notas):
        self.listbox.delete(0, tk.END)
        for nota in notas:
            self.listbox.insert(tk.END, nota)