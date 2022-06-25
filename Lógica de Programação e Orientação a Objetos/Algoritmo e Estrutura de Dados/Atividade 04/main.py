from complex import Complexo
from math import degrees
import format


format.title1(50, "CALCULADORA DE COMPLEXOS", bg_color='CYAN', txt_color='', txt_format='ITALIC+BOLD')
print("Insira 3 números complexos.")

formats = list()
first_parts = list()
second_parts = list()
numbers = list()

for i in range(3):
    is_rect: bool
    while True:
        match input(f"Insira o formato do {i + 1}º número (RECT/POL):\n").upper():
            case 'RECT':
                is_rect = True
                break
            case 'POL':
                is_rect = False
                break
            case _:
                format.msg(50, "ERROR", "INSIRA UMA RESPOSTA VÁLIDA!")
    formats.append(is_rect)

    first_part: str = "o módulo"
    if is_rect:
        first_part = "a parte real"
    while True:
        try:
            first_parts.append(float(input(f"Insira {first_part} do {i + 1}º número:\n")))
        except ValueError:
            format.msg(50, "ERROR", "INSIRA UM NÚMERO VÁLIDO!")
        else:
            break

    second_part: str = "o ângulo"
    if is_rect:
        second_part = "a parte imaginária"
    while True:
        try:
            second_parts.append(float(input(f"Insira {second_part} do {i + 1}º número:\n")))
        except ValueError:
            format.msg(50, "ERROR", "INSIRA UM NÚMERO VÁLIDO!")
        else:
            break
    numbers.append(Complexo(first_parts[i], second_parts[i], formats[i]))
    # numbers[i].mostrar()

format.title1(50, "CALCULADORA DE COMPLEXOS", bg_color='CYAN', txt_color='', txt_format='ITALIC+BOLD')
print('Agora insira a equação, use "Z1", "Z2" e "Z3" para representar os números digitados anteriormente e "R" pra o resultado da equação anterior.')
print('Operações disponíveis: soma(+), subtração(-), multiplicação(*), divisão(/), mostrar vetor(ZX.M)')
print('Digite "SAIR" para encerrar o programa.')
print()
print('OBS: Nãoesqueça de dar um espaço antes e depois de cada opração!')
print()

result = Complexo(0, 0, False)
while True:
    equation = input("Equação: ").strip().upper()
    if equation == 'SAIR':
        break
    func_str = equation.replace("Z1", "numbers[0]").replace("Z2", "numbers[1]").replace("Z3", "numbers[2]").replace("R", "result")
    func_str = func_str.replace('.M', '.mostrar()')
    func_list = func_str.split(' ')
    for i in range(1, len(func_list)):
        if func_list[i] == '*':
            func_list[i] = ".multiplicacao("
            func_list.insert(i+2, ')')
        if func_list[i] == '/':
            func_list[i] = ".divisao("
            func_list.insert(i+2, ')')
    func_str = ''.join(func_list).replace('+', ' + ').replace('-', ' - ')

    func_list = func_str.split(' ')
    for i in range(1, len(func_list)):
        if func_list[i] == '+':
            func_list[i] = ".soma("
            func_list.insert(i + 2, ')')
        if func_list[i] == '-':
            func_list[i] = ".subtracao("
            func_list.insert(i + 2, ')')
    func_str = ''.join(func_list)

    print(f'Função executada: {func_str}')
    try:
        result = eval(func_str)
        print()
        print(f'Parte real do resultado: {result.real:.2f}')
        print(f'Parte imaginária do resultado: {result.imag:.2f}')
        print(f'Módulo do resultado: {result.mod:.2f}')
        print(f'Ângulo do resultado: {degrees(result.teta):.2f}°')
        if 'mostrar' not in func_str:
            result.mostrar()
        print()
    except:
        format.msg(50, "ERROR", "EQUAÇÃO INVÁLIDA!")


