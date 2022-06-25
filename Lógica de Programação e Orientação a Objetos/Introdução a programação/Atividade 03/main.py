from time import sleep
import webbrowser
import random
import format
import sys

explosions = \
    {
        'main': 'https://www.youtube.com/watch?v=jEexefuB62c',
        'expuroooosion': 'https://youtu.be/jar1LTxxAeM?t=14',
        'the best': 'https://youtu.be/dQw4w9WgXcQ',
        'spongebob': 'https://youtu.be/tHYJWn2jLaM',
        'wtf': 'https://youtu.be/eAoR4h6SQGg'
    }

format.title(50, 'ATENÇÃO!!! ISSO NÃO É UM TESTE', bg_color='RED', txt_format='BOLD+ITALIC')
print("Iniciando contagem regressiva...".center(50))
print()
txt_color = 39
for timer in range(10, -1, -1):
    sleep(1)
    if timer <= 3:
        txt_color = 31
    print(f"\033[{txt_color};3;1m\r" + f"{timer:02d}".center(50) + "\033[0m", end='')

print(f"\033[{txt_color};3;1m\r" + f"BOOM!".center(50) + "\033[0m")

random.seed(random.randrange(sys.maxsize))
webbrowser.open(random.choice(list(explosions.values())))
