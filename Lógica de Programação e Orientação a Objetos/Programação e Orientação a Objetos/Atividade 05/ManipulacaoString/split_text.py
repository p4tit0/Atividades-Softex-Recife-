import txtformat

print(
    txtformat.title(50, "SEPARADOR DE TEXTOS", txt_bg_color='YELLOW', txt_format='BOLD+ITALIC', line_format='NONE'))
while True:
    txt = input(txtformat.get_format(txt_format='BOLD+UNDERLINE') + "Insira um texto:\033[0m ")

    print()
    print(txtformat.get_format(txt_format='BOLD') + "Agora escolha como você quer separá-lo:\033[0m")
    print("1 - Apenas números")
    print("2 - Apenas letras")
    print("3 - Apenas letras e numeros")
    print("4 - Separar por substrig")
    print()

    opt: int
    while True:
        try:
            opt = int(input(txtformat.get_format(txt_format='BOLD+UNDERLINE') + "Opção escolhoida:\033[0m "))
            if opt not in range(1, 5):
                print(txtformat.msg1(50, "ERROR", "Insira uma opção válida!", txt_bg_color="RED", line_format="BOLD"))
                continue
        except ValueError as err:
            print(txtformat.msg1(50, "ERROR", "Insira uma opção válida!", txt_bg_color="RED", line_format="BOLD"))
        else:
            break

    result = ''
    if opt == 1:
        for ch in txt:
            if ch.isnumeric():
                result += ch
    elif opt == 2:
        for ch in txt:
            if ch.isalpha():
                result += ch
    elif opt == 3:
        for ch in txt:
            if ch.isalnum():
                result += ch
    elif opt == 4:
        ch = input("Insira a substrig: ")
        result = '\n'.join(txt.split(ch))

    print()
    print(txtformat.get_format(txt_format='BOLD+UNDERLINE') + f"RESULTADO:\033[0m {result}")
    print()
