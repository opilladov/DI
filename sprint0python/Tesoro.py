from random import random

class Tesoro:
    def __init__(self):
        self.beneficios = ["aumento_ataque", "aumento_defensa", "restauracion_salud"]

    def encontrar_tesoro(self, heroe):
        beneficio = random.choice(self.beneficios)
        print(f"El heroe ha encontrado un beneficio {beneficio}")

        if beneficio == "aumento_ataque":
            heroe.ataque = heroe.ataque + 5
            print(f"El ataque de {heroe.nombre} aumenta a {heroe.ataque}")

        elif beneficio == "aumento_defensa":
            heroe.defensa = heroe.defensa + 5
            print(f"La defensa del {heroe.nombre} aumenta a {heroe.defensa}")

        else:
            heroe.salud = heroe.salud_maxima
            print(f"La salud de {heroe.nombre} ha sido restaurada a {heroe.salud}")