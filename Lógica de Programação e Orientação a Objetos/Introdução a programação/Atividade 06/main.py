import format
from datetime import date


format.title(50, "SISTEMA DE CADASTRO", bg_color='GREEN', txt_format='BOLD+ITALIC')
name = input("Insira seu nome: ")

current_year = date.today().year
year: int
while True:
    try:
        year = int(input("Insira seu ano de nascimento: "))
        if not 1922 <= year <= current_year - 1:
            raise Exception("ANO INVÁLIDO!")
    except ValueError:
        format.msg1(50, "ERROR", "O ANO DEVE SER UM NÚMERO")
    except Exception as err:
        format.msg1(50, "ERROR", str(err))
    else:
        break

format.msg1(50, "CADASTRO EFETUADO", f'Nome:{name}\nIdade:{current_year - year}', bg_color='')
