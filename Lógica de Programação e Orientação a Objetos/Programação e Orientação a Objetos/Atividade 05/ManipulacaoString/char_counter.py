import txtformat

print(
    txtformat.title(50, "CONTADOR DE CARACTERES", txt_bg_color='MAGENTA', txt_format='BOLD+ITALIC', line_format='NONE'))
while True:
    txt = input(txtformat.get_format(txt_format='BOLD+UNDERLINE') + "Insira um texto:\033[0m ")

    print()
    print(txtformat.get_format(txt_format='BOLD') + f"O texto inserido tem {len(txt)} caracteres\033[0m")
    print()