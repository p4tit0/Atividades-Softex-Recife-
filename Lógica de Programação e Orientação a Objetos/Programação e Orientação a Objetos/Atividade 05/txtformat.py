def get_format(bg_color: str = 'NONE', txt_color: str = 'NONE', txt_format: str = 'NONE'):
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
    txt_format = []
    for format in formats:
        match format.upper():
            case 'RESET':
                txt_format.append(0)
            case 'BOLD':
                txt_format.append(1)
            case 'FAINT':
                txt_format.append(2)
            case 'ITALIC':
                txt_format.append(3)
            case 'UNDERLINE':
                txt_format.append(4)
            case 'SLOW BLINK':
                txt_format.append(5)
            case 'RAPID BLINK':
                txt_format.append(6)
            case 'INVERT':
                txt_format.append(7)
            case 'HIDE':
                txt_format.append(8)
            case 'STRIKE':
                txt_format.append(9)
            case 'DOUBLE UNDERLINE':
                txt_format.append(21)
            case _:
                txt_format .append(10)
    format_str = f"\033[{bg_color_num};{txt_color_num}"
    for f in txt_format:
        format_str += f";{f}"
    format_str += "m"
    return format_str


def title(size: int, txt: str, line: str = '-=-', txt_bg_color: str = 'NONE', txt_color: str = 'NONE', txt_format: str = 'NONE', line_bg_color: str = None, line_color: str = None, line_format: str = None):

    if line_bg_color is None:
        line_bg_color = txt_bg_color
    if line_color is None:
        line_color = txt_color
    if line_format is None:
        line_format = txt_format

    txt_format_str = get_format(txt_bg_color, txt_color, txt_format)
    line_format_str = get_format(line_bg_color, line_color, line_format)

    result = line_format_str + line * (size // len(line)) + line[:size % len(line)] + "\033[0m\n"
    for title_line in txt.split('\n'):
        result += txt_format_str + title_line.center(size) + "\033[0m\n"
    result += line_format_str + line * (size // len(line)) + line[:size % len(line)] + "\033[0m"
    return result


def msg1(size: int, title: str, txt: str, line: str = '-', txt_bg_color: str = 'NONE', txt_color: str = 'NONE', txt_format: str = 'NONE', line_bg_color: str = None, line_color: str = None, line_format: str = None):

    if line_bg_color is None:
        line_bg_color = txt_bg_color
    if line_color is None:
        line_color = txt_color
    if line_format is None:
        line_format = txt_format

    txt_format_str = get_format(txt_bg_color, txt_color, txt_format)
    line_format_str = get_format(line_bg_color, line_color, line_format)

    result = line_format_str + f" {title} ".center(size, line) + "\033[0m\n"
    for message in txt.split('\n'):
        result += txt_format_str + message.center(size) + "\033[0m\n"
    result += line_format_str + line * (size // len(line)) + line[:size % len(line)] + "\033[0m"
    return result


def msg2(size: int, txt: str, bg_color: str = '', txt_color: str = '', txt_format: str = ''):
    txt_format_str = get_format(bg_color, txt_color, txt_format)

    for message in txt.split('\n'):
        print(txt_format_str + message + " " * (size - len(message)) + "\033[0m")