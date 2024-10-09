class Heroe:
    def __init__(self, nombre):
        self.nombre = nombre
        self.ataque = 10
        self.defensa = 5
        self.salud = 100
        self.salud_maxima = 100
        self.defensa_temporal = 0

    def atacar(self, enemigo):
        print(f"Heroe ataca a {enemigo.nombre}")
        dano = self.ataque - self.defensa
        if dano > 0:
            print(f"El enemigo {enemigo.nombre} ha recibido {dano} puntos de daño")
        else:
            print(f"El eneimgo a bloqueado el ataque")

    def curarse(self):
        cura = 20
        self.salud = min(self.salud + cura, self.salud_maxima)
        print(f"Heroe se ha curado. Salud actual: {self.salud}")

    def defenderse(self):
        self.defensa_temporal = 5
        print(f"Heroe se defiende. Defensa aumentada temporalmente a {self.defensa + self.defensa_temporal}")

    def reset_defensa(self):
        self.defensa_temporal = 0
        print(f"La defensa de {self.nombre} vuelve a la normalidad")

    def esta_vivo(self):
        return self.salud > 0