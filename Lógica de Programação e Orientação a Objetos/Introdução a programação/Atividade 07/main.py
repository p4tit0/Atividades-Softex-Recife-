import format
import enum


class Candidatos(enum.Enum):
    candidato_X = 889
    candidato_Y = 847
    candidato_Z = 515
    branco = 0


class InputErrors(enum.Enum):
    err_negative_input = 1
    err_input_out_of_range = 2
    err_invalid_input = 3


def int_input(msg: str):
    user_input: int
    try:
        user_input = int(input(msg))
        if user_input < 0:
            return InputErrors.err_negative_input
    except ValueError:
        return InputErrors.err_invalid_input
    else:
        return user_input


def limited_str_input(msg, possible_inputs):
    user_input = input(msg).upper()
    if user_input in possible_inputs:
        return user_input
    return InputErrors.err_input_out_of_range


cont_votos = \
    {
        'x': 0,
        'y': 0,
        'z': 0,
        'branco': 0,
        'nulos': 0
    }

while True:
    format.title(50, 'URNA DIGITAL', bg_color='CYAN', txt_format='BOLD+ITALIC')
    print('Escolha um dos seguntes candidatos:')
    for candidato in Candidatos:
        print(f'{candidato.name.capitalize()} - {candidato.value}')
    print()

    voto: int
    while True:
        voto = int_input('Insira seu voto: ')
        if voto == InputErrors.err_negative_input:
            format.msg1(50, 'ERROR', 'O VOTO DEVE SER UM NÚMERO POSITIVO')
        elif voto == InputErrors.err_invalid_input:
            format.msg1(50, 'ERROR', 'O VOTO DEVE SER UM NÚMERO')
        else:
            break
    if voto == Candidatos.candidato_X.value:
        cont_votos['x'] += 1
    elif voto == Candidatos.candidato_Y.value:
        cont_votos['y'] += 1
    elif voto == Candidatos.candidato_Z.value:
        cont_votos['z'] += 1
    elif voto == Candidatos.branco.value:
        cont_votos['branco'] += 1
    else:
        cont_votos['nulos'] += 1

    print('-' * 50)
    while True:
        end = limited_str_input('Encerrar votação (S/N)? ', ('S', 'N'))
        if end == InputErrors.err_input_out_of_range:
            format.msg1(50, 'ERROR', 'A RESPOSTA SÒ PODE SER SIM(S) OU NÂO(N)')
            continue
        break
    if end == 'S':
        break

vencedor = {'nome': '', 'votos': 0}
msg = ''
for candidato in Candidatos:
    n_votos = cont_votos[candidato.name.split("_")[-1].lower()]
    msg += f'{candidato.name.capitalize()}: {n_votos}\n'
    if n_votos > vencedor['votos'] and candidato.name != 'branco':
        vencedor['nome'] = candidato.name.capitalize()
        vencedor['votos'] = n_votos

msg += f'Nulos: {cont_votos["nulos"]}'
format.msg1(50, 'RESULTADOS', msg, bg_color='')
format.msg1(50, 'VENCEDOR', vencedor['nome'], bg_color='GREEN')
