import format


def int_input(msg: str, error_msg: str):
    user_input: int
    while True:
        try:
            user_input = int(input(msg))
        except ValueError:
            format.msg1(50, "ERROR", error_msg)
        else:
            break
    return user_input

def calculadora(n1, n2, op):
    if op == 1:
        return n1 + n2
    elif op == 2:
        return n1 - n2
    elif op == 3:
        return n1 * n2
    elif op == 4:
        return n1 / n2
    else:
        return 0


format.title(50, "CALCULADORA", bg_color='BLUE', txt_format='BOLD+ITALIC')
while True:
    n1 = int_input("Primeiro número: ", "INSIRA UM NÚMERO")
    n2 = int_input("Segundo número: ", "INSIRA UM NÚMERO")
    print("Escolha uma das seguintes operações:")
    print(" 1 - SOMA (+)")
    print(" 2 - SUBTRAÇÃO (-)")
    print(" 3 - MULTIPLICAÇÃO (*)")
    print(" 4 - DIVISÃO (/)\n")
    op = int_input("Operação escolhida: ", "INSIRA UM NÚMERO")
    format.msg1(50, "RESULTADO", f"{calculadora(n1, n2, op)}", bg_color='', txt_format='BOLD')
