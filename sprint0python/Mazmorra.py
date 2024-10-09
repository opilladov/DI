import random

from Monstruo import Monstruo
from Tesoro import Tesoro

class Mazmorra:
    def __init__(self, heroe):
        self.heroe = heroe
        self.monstruos = [Monstruo("Dragon", 8, 3, 30),
                          Monstruo("Esbirro", 5, 2, 20),
                          Monstruo("Gigante", 12, 4, 40)]
        self.tesoro = Tesoro()

    def jugar(self):
        print("Heroe entra en la mazmorra")
        while len(self.monstruos) > 0:
            monstruo = random.choice(self.monstruos)
            print(f"Te has encontrado con {monstruo.nombre}")
            self.enfrentar(monstruo)
            if not self.heroe.esta_vivo():
                print("El heroe ha sido derrotado en la mazmorra")
                return
            self.buscar_tesoro()
        print(f"{self.heroe.nombre} ha derrotado a todos los monstruos y ha conquistado la mazmorra")

    def enfrentar(self, enemigo):
        while enemigo.esta_vivo() and self.heroe.esta_vivo():
            print("¿Qué deseas hacer?")
            print("1. Atacar")
            print("2. Defender")
            print("3. Curarse")
            opcion = input("Selecciona una opción: ")
            if opcion == "1":
                self.heroe.atacar(enemigo)
            elif opcion == "2":
                self.heroe.defenderse()
            elif opcion == "3":
                self.heroe.curarse()
            else:
                print("Opción no válida")

            if enemigo.esta_vivo():
                enemigo.atacar(self.heroe)
                self.heroe.reset_defensa()

    def buscar_tesoro(self):
        print("Buscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)