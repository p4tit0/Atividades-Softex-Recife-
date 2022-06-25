import format


def pegar_nota(msg: str, error_msg: str):
    """
    Função para tratar o input da nota

    :param msg: Mensagem enviada ao pedir pela nota
    :param error_msg: Mensagem enviada caso o input seja inválido
    :return: (float) Nota
    """
    user_input: float
    while True:
        try:
            user_input = float(input(msg))
            if not (0 <= user_input <= 10):
                format.msg(50, "ERROR", error_msg)
                continue
        except ValueError:
            format.msg(50, "ERROR", error_msg)
        else:
            break
    return user_input


while True:
    format.title1(50, "SISTEMA DE MÉDIAS", bg_color='YELLOW', txt_format='BOLD+ITALIC')
    print("Bem-vindo ao sistema de médias!")
    print("Insira os dados do aluno e saiba se ele foi aprovado ou não.\n")

    nome = input("Insira o nome do aluno: ").capitalize().strip()

    nota1 = pegar_nota(f"Insira a 1ª nota de {nome}: ", "INSIRA UMA NOTA VÁLIDA")
    nota2 = pegar_nota(f"Insira a 2ª nota de {nome}: ", "INSIRA UMA NOTA VÁLIDA")

    faltas: int
    while True:
        try:
            faltas = int(input(f"Insira o número de faltas de {nome}: "))
            if faltas < 0:
                format.msg(50, "ERROR", "INSIRA UM VALOR MAIOR OU IGUAL A 0")
                continue
        except ValueError:
            format.msg(50, "ERROR", "INSIRA UM NÚMERO")
        else:
            break

    media = (nota1 + nota2) / 2

    if media < 7:
        format.msg(50, "REPROVADO(A)", nome)
    elif faltas > 3:
        format.msg(50, "REPROVADO(A) POR FALTA", nome)
    else:
        format.msg(50, "APROVADO(A)", nome, bg_color='GREEN')
    print(f"NOTA 1: {nota1:.2f}")
    print(f"NOTA 2: {nota2:.2f}")
    print(f"MÉDIA: {media:.2f}\n")

    print(f"N° DE FALTAS: {faltas}")
    print("-" * 50)

    end: str
    while True:
        end = input("Encerrar o sistema (S/N)? ").upper()
        match end:
            case 'S':
                print("-" * 50)
                break
            case 'N':
                break
            case _:
                format.msg(50, "ERROR", "INSIRA UMA RESPOSTA VÁLIDA")
    if end == 'S':
        break

print("Até a próxima")

