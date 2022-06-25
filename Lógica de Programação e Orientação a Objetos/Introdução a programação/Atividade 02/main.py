import format


def int_input(msg: str, error_msg: str):
    user_input: int
    while True:
        try:
            user_input = int(input(msg))
            if user_input < 0:
                format.msg1(50, "ERROR", error_msg)
                continue
        except ValueError:
            format.msg1(50, "ERROR", error_msg)
        else:
            break
    return user_input


def limited_str_input(msg, error_msg, possible_inputs):
    user_input: str
    while True:
        user_input = input(msg).upper()
        if user_input in possible_inputs:
            break
        else:
            format.msg(50, "ERROR", error_msg)
    return user_input


while True:
    format.title(50, "DETECTOR DE CATEGORIA", bg_color='CYAN', txt_format='BOLD+ITALIC')
    print("Bem-vindo ao detector de categoria!")
    print(
        "Insira as características do veículo e descubra qual é a categoria de habilitação necessária para dirigi-lo.\n")

    n_pessoas = int_input("Quantas pessoas cabem no veículo? ", "INSIRA UMA RESPOSTA VÁLIDA")
    n_rodas = int_input("Quantas rodas tem o veículo? ", "INSIRA UMA RESPOSTA VÁLIDA")

    peso: float
    while True:
        try:
            peso = round(float(input("Qual o peso do veículo (Kg)? ")), 2)
            if peso < 0:
                format.msg1(50, "ERROR", "INSIRA UMA RESPOSTA VÁLIDA")
                continue
        except ValueError:
            format.msg1(50, "ERROR", "INSIRA UMA RESPOSTA VÁLIDA")
        else:
            break

    print()

    if 2 <= n_rodas <= 3:
        format.title(50, "\033[31;3;1m" + "VEÍCULO DE CATEGORIA A".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')
    elif n_rodas == 4 and n_pessoas <= 8 and peso <= 3500:
        format.title(50, "\033[32;3;1m" + "VEÍCULO DE CATEGORIA B".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')
    elif n_rodas >= 4 and 3500 <= peso <= 6000:
        format.title(50, "\033[33;3;1m" + "VEÍCULO DE CATEGORIA C".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')
    elif n_rodas >= 4 and n_pessoas > 8:
        format.title(50, "\033[34;3;1m" + "VEÍCULO DE CATEGORIA D".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')
    elif n_rodas >= 4 and peso > 6000:
        format.title(50, "\033[35;3;1m" + "VEÍCULO DE CATEGORIA E".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')
    else:
        format.title(50, "\033[37;3;1m" + "ESSE VEÍCULO NÃO EXISTE".center(50) + "\033[0m", line='-', txt_format='BOLD+ITALIC')


    if limited_str_input("Encerrar o programa (S/N)? ", "INSIRA UMA RESPOSTA VÁLIDA", ('S', 'N')) == 'S':
        break
    print("-" * 50)

print("Até a próxima")
