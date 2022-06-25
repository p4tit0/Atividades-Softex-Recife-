def get_format(bg_color: str = '', txt_color: str = '', txt_format: str = ''):
    txt_color_num = 30
    if txt_color.upper().split(' ')[0] == 'BRIGHT':
        txt_color_num = 90

    match txt_color.upper().split(' ')[-1]:
        case 'BLACK':
            txt_color_num += 0
        case 'RED':
            txt_color_num += 1
        case 'GREEN':
            txt_color_num += 2
        case 'YELLOW':
            txt_color_num += 3
        case 'BLUE':
            txt_color_num += 4
        case 'MAGENTA':
            txt_color_num += 5
        case 'CYAN':
            txt_color_num += 6
        case 'WHITE':
            txt_color_num += 7
        case _:
            txt_color_num += 9

    bg_color_num = 40
    if bg_color.upper().split(' ')[0] == 'BRIGHT':
        bg_color_num = 100
    match bg_color.upper().split(' ')[-1]:
        case 'BLACK':
            bg_color_num += 0
        case 'RED':
            bg_color_num += 1
        case 'GREEN':
            bg_color_num += 2
        case 'YELLOW':
            bg_color_num += 3
        case 'BLUE':
            bg_color_num += 4
        case 'MAGENTA':
            bg_color_num += 5
        case 'CYAN':
            bg_color_num += 6
        case 'WHITE':
            bg_color_num += 7
        case _:
            bg_color_num += 9

    formats = txt_format.split('+')
    txt_format = ''
    for format in formats:
        match format.upper():
            case 'RESET':
                txt_format += ';0'
            case 'BOLD':
                txt_format += ';1'
            case 'FAINT':
                txt_format += ';2'
            case 'ITALIC':
                txt_format += ';3'
            case 'UNDERLINE':
                txt_format += ';4'
            case 'SLOW BLINK':
                txt_format += ';5'
            case 'RAPID BLINK':
                txt_format += ';6'
            case 'INVERT':
                txt_format += ';7'
            case 'HIDE':
                txt_format += ';8'
            case 'STRIKE':
                txt_format += ';9'
            case 'DOUBLE UNDERLINE':
                txt_format += ';21'
            case _:
                txt_format += ';10'
    return bg_color_num, txt_color_num, txt_format


def title(size: int, txt: str, line: str = '-=-', bg_color: str = '', txt_color: str = '', txt_format: str = ''):
    bg_color_num, txt_color_num, txt_format = get_format(bg_color, txt_color, txt_format)

    print(f"\033[{bg_color_num};{txt_color_num}m" + line * (size // len(line)) + line[:size % len(line)] + "\033[0m")
    print(f"\033[{bg_color_num};{txt_color_num}{txt_format}m{txt.center(size)}\033[0m")
    print(f"\033[{bg_color_num};{txt_color_num}m" + line * (size // len(line)) + line[:size % len(line)] + "\033[0m")


def msg1(size: int, title: str, txt: str, line: str = '-', bg_color: str = 'RED', txt_color: str = '',
txt_format: str = ''):
    bg_color_num, txt_color_num, txt_format = get_format(bg_color, txt_color, txt_format)

    print(f"\033[{bg_color_num};{txt_color_num}m" + f" {title} ".center(size, line) + "\033[0m")
    print(f"\033[{bg_color_num};{txt_color_num}{txt_format}m{txt.center(size)}\033[0m")
    print(f"\033[{bg_color_num};{txt_color_num}m" + line * (size // len(line)) + line[:size % len(line)] + "\033[0m")


def msg2(size: int, txt: str, bg_color: str = '', txt_color: str = '', txt_format: str = ''):
    bg_color_num, txt_color_num, txt_format = get_format(bg_color, txt_color, txt_format)

    print(f"\033[{bg_color_num};{txt_color_num}{txt_format}m{txt}" + " " * (size - len(txt)) + "\033[0m")
