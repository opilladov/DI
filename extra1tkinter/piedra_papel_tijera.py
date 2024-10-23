import tkinter as tk
import random
from tkinter import messagebox

def jugar(respuesta):
    opciones = ['Piedra', 'Papel', 'Tijera']
    eleccion_computadora = random.choice(opciones)

    mensaje = ""

    if respuesta == eleccion_computadora:
        mensaje = "Empate"

    elif respuesta == "Papel" and eleccion_computadora == "Piedra":
        mensaje = "El jugador uno saca papel, la maquina saca piedra.\nGana el jugador uno, papel gana a piedra"
        jugador.set(jugador.get()+1)

    elif respuesta == "Piedra" and eleccion_computadora == "Tijera":
        mensaje = "El jugador uno saca piedra, la maquina saca tijera.\nGana el jugador uno, piedra gana a tijera"
        jugador.set(jugador.get()+1)

    elif respuesta == "Tijera" and eleccion_computadora == "Papel":
        mensaje = "El jugador uno saca tijera, la maquina saca papel.\nGana el jugador uno, tijera gana a papel"
        jugador.set(jugador.get()+1)

    elif respuesta == "Piedra" and eleccion_computadora == "Papel":
        mensaje = "El jugador uno saca piedra, la maquina saca papel.\nGana la maquina, papel gana a piedra"
        maquina.set(maquina.get()+1)

    elif respuesta == "Tijera" and eleccion_computadora == "Piedra":
        mensaje = "El jugador uno saca tijera, la maquina saca piedra.\nGana la maquina, piedra gana a tijera"
        maquina.set(maquina.get()+1)

    elif respuesta == "Papel" and eleccion_computadora == "Tijera":
        mensaje = "El jugador uno saca papel, la maquina saca tijera.\nGana la maquina, tijera gana a papel"
        maquina.set(maquina.get()+1)

    if jugador.get() == 3:
        mensaje = "El jugador es el ganador de la partida"

    elif maquina.get() == 3:
        mensaje = "La maquina es la ganadora de la partida"

    messagebox.showinfo("Resultado", mensaje)

def partida_un_jugador():
    jugador.set(0)
    maquina.set(0)
    un_jugador = tk.Toplevel(ventana)
    un_jugador.geometry("500x500")

    etiqueta = tk.Label(un_jugador, text="Elige una opción: ")
    etiqueta.grid(column=0,row=0)

    piedra = tk.Button(un_jugador, text="Piedra", command=lambda: jugar("Piedra"))
    piedra.grid(column=1,row=0)
    papel = tk.Button(un_jugador, text="Papel", command=lambda: jugar("Papel"))
    papel.grid(column=2, row=0)
    tijera = tk.Button(un_jugador, text="Tijera", command=lambda: jugar("Tijera"))
    tijera.grid(column=3, row=0)

def jugar2(respuesta, respuesta2):
    mensaje = ""

    if respuesta == respuesta2:
        mensaje = "Empate"

    elif respuesta == "Papel" and respuesta2 == "Piedra":
        mensaje = "El jugador uno saca papel, el jugador dos saca piedra.\nGana el jugador uno, papel gana a piedra"
        jugador_a.set(jugador_a.get() + 1)

    elif respuesta == "Piedra" and respuesta2 == "Tijera":
        mensaje = "El jugador uno saca piedra, el jugador dos saca tijera.\nGana el jugador uno, piedra gana a tijera"
        jugador_a.set(jugador_a.get() + 1)

    elif respuesta == "Tijera" and respuesta2 == "Papel":
        mensaje = "El jugador uno saca tijera, el jugador dos saca papel.\nGana el jugador uno, tijera gana a papel"
        jugador_a.set(jugador_a.get() + 1)

    elif respuesta == "Piedra" and respuesta2 == "Papel":
        mensaje = "El jugador uno saca piedra, el jugador dos saca papel.\nGana el jugador dos, papel gana a piedra"
        jugador_b.set(jugador_b.get() + 1)

    elif respuesta == "Tijera" and respuesta2 == "Piedra":
        mensaje = "El jugador uno saca tijera, el jugador dos saca piedra.\nGana el jugador dos, piedra gana a tijera"
        jugador_b.set(jugador_b.get() + 1)

    elif respuesta == "Papel" and respuesta2 == "Tijera":
        mensaje = "El jugador uno saca papel, el jugador dos saca tijera.\nGana el jugador dos, tijera gana a papel"
        jugador_b.set(jugador_b.get() + 1)

    if jugador_a.get() == 3:
        mensaje = "El jugador 1 es el ganador de la partida"

    elif jugador_b.get() == 3:
        mensaje = "El jugador 2 es el ganador de la partida"

    messagebox.showinfo("Resultado", mensaje)

def partida_dos_jugadores():
    dos_jugadores = tk.Toplevel(ventana)
    dos_jugadores.geometry("500x500")

    etiqueta = tk.Label(dos_jugadores, text="Jugador 1 elige una opción: ")
    etiqueta.grid(column=0, row=0)

    piedra = tk.Button(dos_jugadores, text="Piedra", command=lambda: eleccion_jugador_b.set("Piedra"))
    piedra.grid(column=1, row=0)
    papel = tk.Button(dos_jugadores, text="Papel", command=lambda: eleccion_jugador_b.set("Papel"))
    papel.grid(column=2, row=0)
    tijera = tk.Button(dos_jugadores, text="Tijera", command=lambda: eleccion_jugador_b.set("Tijera"))
    tijera.grid(column=3, row=0)

    etiqueta2 = tk.Label(dos_jugadores, text="Jugador 2 elige una opción: ")
    etiqueta2.grid(column=0, row=3)

    piedra2 = tk.Button(dos_jugadores, text="Piedra", command=lambda: jugar2("Piedra", eleccion_jugador_b.get()))
    piedra2.grid(column=1, row=3)
    papel2 = tk.Button(dos_jugadores, text="Papel", command=lambda: jugar2("Papel", eleccion_jugador_b.get()))
    papel2.grid(column=2, row=3)
    tijera2 = tk.Button(dos_jugadores, text="Tijera", command=lambda: jugar2("Tijera", eleccion_jugador_b.get()))
    tijera2.grid(column=3, row=3)

def salir():
    ventana.destroy()

ventana = tk.Tk()
ventana.title("Juego")
ventana.geometry("500x500")

jugador = tk.IntVar()
maquina = tk.IntVar()

jugador_a = tk.IntVar()
jugador_b = tk.IntVar()
eleccion_jugador_b = tk.StringVar()

menu_bar = tk.Menu(ventana)

menu_juego = tk.Menu(menu_bar, tearoff=0)
menu_juego.add_command(label="Un jugador", command=partida_un_jugador)
menu_juego.add_command(label="Dos jugadores", command=partida_dos_jugadores)
menu_juego.add_separator()
menu_juego.add_command(label="Salir", command=salir)

menu_bar.add_cascade(label="Juego", menu=menu_juego)

ventana.config(menu=menu_bar)

ventana.mainloop()