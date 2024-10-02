from operaciones import suma, resta, multiplicacion, division

def calculadora():
    while True:
        try:
            num1 = float(input("Introduce el primer número: "))
            num2 = float(input("Introduce el segundo número: "))
        except ValueError:
            print("Introduce números válidos")
            continue

        print("1: Suma")
        print("2: Resta")
        print("3: Multiplicación")
        print("4: División")

        operacion = input("Introduce el número de la operación (1/2/3/4): ")

        if operacion == '1':
            print(f"Resultado: {suma(num1, num2)}")
        elif operacion == '2':
            print(f"Resultado: {resta(num1, num2)}")
        elif operacion == '3':
            print(f"Resultado: {multiplicacion(num1, num2)}")
        elif operacion == '4':
            print(f"Resultado: {division(num1, num2)}")
        else:
            print("Operación no válida")
            continue

        continuar = input("¿Deseas realizar otra operación? (s/n): ").lower()
        if continuar != 's':
            print("Calculadora finalizada")
            break

if __name__ == "__main__":
    calculadora()