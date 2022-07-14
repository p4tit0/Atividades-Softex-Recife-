import txtformat


print(
    txtformat.title(50, "CONVERSOR DE CAPTALIZAÇÃO", txt_bg_color='CYAN', txt_format='BOLD+ITALIC', line_format='NONE'))
while True:
    txt = input(txtformat.get_format(txt_format='BOLD+UNDERLINE') + "Insira um texto:\033[0m ")

    print()
    print(txtformat.get_format(txt_format='BOLD') + "Agora escolha a captalização:\033[0m")
    print("1 - Captalização de sentença")
    print("2 - Captalização de Título")
    print("3 - MAIÚSCULA")
    print("4 - minuscula")
    print("5 - cApTaLiZaÇãO aLtErNaDa")
    print("6 - InVeRtEr cApTaLiZaÇãO")
    print()

    opt: int
    while True:
        try:
            opt = int(input(txtformat.get_format(txt_format='BOLD+UNDERLINE') + "Captalização escolhoida:\033[0m "))
            if opt not in range(1, 7):
                print(txtformat.msg1(50, "ERROR", "Insira uma opção válida!", txt_bg_color="RED", line_format="BOLD"))
                continue
        except ValueError as err:
            print(txtformat.msg1(50, "ERROR", "Insira uma opção válida!", txt_bg_color="RED", line_format="BOLD"))
        else:
            break

    if opt == 1:
        txt_split = txt.split('. ')
        for i in range(len(txt_split)):
            txt_split[i] = txt_split[i].capitalize()
        txt = '. '.join(txt_split)
    elif opt == 2:
        txt = txt.title()
    elif opt == 3:
        txt = txt.upper()
    elif opt == 4:
        txt = txt.lower()
    elif opt == 5:
        result = ''
        for i in range(len(txt)):
            if i % 2 == 0:
                result += txt[i].lower()
            else:
                result = txt[i].upper()
        txt = result
    elif opt == 6:
        txt = txt.swapcase()

    print()
    print(txtformat.get_format(txt_format='BOLD+UNDERLINE') + f"RESULTADO:\033[0m {txt}")
    print()
