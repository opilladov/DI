import threading
from tkinter import messagebox

import requests
from PIL import Image, ImageTk
import io

class Controlador:
    def __init__(self, modelo, vista):
        self.modelo = modelo
        self.vista = vista

        self.vista.boton_agregar.config(command=self.agregar_nota)
        self.vista.boton_eliminar.config(command=self.eliminar_nota)
        self.vista.boton_guardar.config(command=self.guardar_notas)
        self.vista.boton_cargar.config(command=self.cargar_notas)
        self.vista.boton_descargar.config(command=self.descargar_imagen)

        self.vista.root.bind("<Button-1>", self.actualizar_coordenadas)

        self.cargar_notas()

    def agregar_nota(self):
        nueva_nota = self.vista.entry_nota.get()
        if nueva_nota:
            self.modelo.agregar_nota(nueva_nota)
            self.actualizar_listbox()
            self.vista.entry_nota.delete(0, 'end')

    def eliminar_nota(self):
        seleccion = self.vista.listbox.curselection()
        if seleccion:
            indice = seleccion[0]
            self.modelo.eliminar_nota(indice)
            self.actualizar_listbox()

    def guardar_notas(self):
        self.modelo.guardar_notas()
        messagebox.showinfo("Información", "Nota/s guardada/s")

    def cargar_notas(self):
        self.modelo.cargar_notas()
        self.actualizar_listbox()

    def actualizar_listbox(self):
        notas = self.modelo.obtener_notas()
        self.vista.actualizar_listbox(notas)

    def actualizar_coordenadas(self, event):
        self.vista.label_coordenadas.config(text=f"Coordenadas: ({event.x}, {event.y})")

    def descargar_imagen(self):
        threading.Thread(target=self.descargar_imagen_github).start()

    def descargar_imagen_github(self):
        url = "https://github.com/opilladov/DI/blob/main/sprint2tkinter/imagen1.png"
        try:
            respuesta = requests.get(url)
            if respuesta.status_code == 200:
                imagen = Image.open(io.BytesIO(respuesta.content))
                imagen.thumbnail((300, 300))
                imagen_tk = ImageTk.PhotoImage(imagen)

                self.vista.root.after(0, lambda: self.vista.label_imagen.config(image=imagen_tk))
                self.vista.label_imagen.image = imagen_tk
            else:
                messagebox.showerror("Error", "No se pudo descargar la imagen.")
        except Exception as e:
            messagebox.showerror("Error", f"Ocurrió un error: {e}")