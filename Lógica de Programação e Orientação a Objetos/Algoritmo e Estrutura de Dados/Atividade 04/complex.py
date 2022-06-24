import math
import numpy as np
import matplotlib.pyplot as plt


def quadrante_do_vetor(r, i):
    """
    Detecta em qual quadrante o vetor do número complexo informado está posicionado

    :param r: (float) Parte real do número complexo
    :param i: (float) Parte imáginária do número complexo
    :return: (int) Número do quadrante em que o vetor se encontra
    """
    if r >= 0:
        return 1 if i >= 0 else 4
    else:
        return 2 if i >= 0 else 3

class Complexo:
    def __init__(self, real_mod: float, imag_teta: float, is_rect: bool = True):
        """
        Métudo construtor da classe

        :param real_mod: Parte real ou módulo do número complexo
        :param imag_teta: Parte imaginária ou ângulo do número complexo
        :param is_rect: Booleana que indica se o número inserido está na forma retangular
        """

        if is_rect:
            self.real: float = real_mod
            self.imag: float = imag_teta

            quad = quadrante_do_vetor(self.real, self.imag)
            self.teta: float = math.atan(abs(imag_teta)/abs(real_mod))
            match quad:
                case 2:
                    self.teta = math.pi - self.teta
                case 3:
                    self.teta = math.pi + self.teta
                case 4:
                    self.teta = 2 * math.pi - self.teta

            self.mod: float = math.sqrt(real_mod ** 2 + imag_teta ** 2)
        else:
            self.teta: float = math.radians(imag_teta)
            self.mod: float = real_mod

            self.real: float = real_mod * math.cos(self.teta)
            self.imag: float = real_mod * math.sin(self.teta)

    def soma(self, z):
        """
        Soma o atual número complexo com o que é passdo como parâmetero

        :param z: Numero complexo que sera somado
        :return: (complex) Resultado da soma
        """
        return Complexo(self.real + z.real, self.imag + z.imag)

    def subtracao(self, z):
        """
        Subtrai o que é passdo como parâmetero do atual número complexo

        :param z: Numero complexo que sera subtraido
        :return: (complex) Resultado da subtração
        """
        return Complexo(self.real - z.real, self.imag - z.imag)

    def multiplicacao(self, z):
        """
        Multiplica o atual número complexo com o que é passdo como parâmetero

        :param z: Numero complexo que sera multiplicado
        :return: (complex) Resultado da multiplicação
        """
        return Complexo(self.mod * z.mod, math.degrees(self.teta + z.teta), False)

    def divisao(self, z):
        """
        Divide o atual número complexo pelo o que é passdo como parâmetero

        :param z: Divisor
        :return: (complex) Resultado da divisão
        """
        return Complexo(self.mod / z.mod, math.degrees(self.teta - z.teta), False)

    def mostrar(self):

        def ponto_de_intercessao(m, r, quad):
            """
            Calcula um dos ponto onde uma reta (que passa pela origem do plano) e uma circunferência (que tem seu centro na origem do plano) se encontram

            :param m: coeficiente angular da equação da reta
            :param r: coeficiente linear da equação da reta
            :param quad: quadrante do encontro desejado
            :return: (int) (int) cordenadas x e y do ponto onde houve o encontro
            """
            c1 = 1 + m ** 2

            delta = - 4.0 * c1 * (-r ** 2)

            x1 = (np.sqrt(delta)) / (2.0 * c1)
            x2 = (- np.sqrt(delta)) / (2.0 * c1)

            y1 = m * x1
            y2 = m * x2

            match quad:
                case 1:
                    return x1 if (x1 > x2) else x2, y1 if (y1 > y2) else y2
                case 2:
                    return x1 if (x1 < x2) else x2, y1 if (y1 > y2) else y2
                case 3:
                    return x1 if (x1 < x2) else x2, y1 if (y1 < y2) else y2
                case 4:
                    return x1 if (x1 > x2) else x2, y1 if (y1 < y2) else y2

        label_font = {
            'family': 'serif',
            'weight': 'normal',
            'style': 'normal',
            'size': 'x-large',
        }  # Fonte usada nas labels dos eixos
        title_font = {
            'family': 'serif',
            'weight': 'bold',
            'style': 'normal',
            'size': 'xx-large',
        }  # Fonte usada no título
        text_font = {
            'family': 'serif'
        }  # Fonte usada nos textos

        fig, ax = plt.subplots()
        ax.quiver(0, 0, self.real, self.imag, width=0.005, color='r', angles='xy', scale_units='xy', scale=1,
                  zorder=4)  # Desenha o vetor

        quad = quadrante_do_vetor(self.real, self.imag)  # Detecta em qual quadrante o vetor está

        cord_maior = abs(self.real) if abs(self.real) > abs(self.imag) else abs(self.imag)  # maior cordenada absoluta

        raio = cord_maior * 0.2  # Calcula o raio do arco do ângulo (36% da maior cordenada)

        # Acha o ponto onde o arco do ângulo se encontra com o vetor
        xp, yp = ponto_de_intercessao(self.imag / self.real, raio, quad)

        teta = np.linspace(0, self.teta, 100)
        xa = raio * np.cos(teta)
        ya = raio * np.sin(teta)

        plt.plot(xa, ya, color='black', zorder=3, alpha=0.75)  # Desenha o arco

        meio_angulo = self.teta / 2.0  # Calcula a metade do angulo

        # Acha as coordenadas da notacão do angulo
        teta_x = (raio + 0.045) * np.cos(np.pi/16)
        teta_y = (raio + 0.045) * np.sin(np.pi/16)

        angulo = round(np.rad2deg(abs(self.teta)), 2)  # Converte o ângulo de radianos para graus e arredonda
        # Exibe o âgulo
        ax.text(teta_x, teta_y, f"$\\theta$ = {angulo}°", fontdict=text_font)
        # Exibe o módulo
        ax.text(self.real / 2 - self.real * 0.25, self.imag / 2 - self.imag * 0.1,
                f"|z| = {round(np.sqrt(self.real ** 2 + self.imag ** 2), 2)}",
                zorder=5, fontdict=text_font, ha='right' if 90 < angulo < 270 else 'left', va='center',
                rotation_mode='anchor', rotation=angulo + 180 if 90 < angulo < 270 else angulo,
                transform_rotates_text=True)

        ax.plot([0, self.real], [self.imag, self.imag], color="black", linestyle="--",
                alpha=0.75)  # Desenha a linha tracejada horizontal
        ax.plot([self.real, self.real], [0, self.imag], color="black", linestyle="--",
                alpha=0.75)  # Desenha a linha tracejada vertical

        # Desenha um ponto onde as linhas encontram os eixos
        plt.scatter(0, self.imag, color='black')
        plt.scatter(self.real, 0, color='black')

        # Exibe os valores reais e imaginários do número
        ax.text(self.real + 0.03 * cord_maior, 0 + 0.015 * cord_maior, f"a = {self.real:.2f}", fontdict=text_font)
        ax.text(0 + 0.015 * cord_maior, self.imag + 0.03 * cord_maior, f"b = {self.imag:.2f}", fontdict=text_font)

        # Define as propriedades do plano
        ax.set_aspect('equal')
        ax.set_title('Representação vetorial', fontdict=title_font, pad=10)
        ax.set_xlabel("Re", loc='right', fontdict=label_font)
        ax.set_ylabel("Im", loc='top', fontdict=label_font)
        ax.grid(True, which='both', zorder=1)

        # Move as marcações do eixo x para o 0
        ax.spines['left'].set_position('zero')

        # Esconde a borda direita
        ax.spines['right'].set_color('none')
        ax.yaxis.tick_left()

        # Move as marcações do eixo y para o 0
        ax.spines['bottom'].set_position('zero')

        # Esconde a borda superior
        ax.spines['top'].set_color('none')
        ax.xaxis.tick_bottom()

        # Define os limites do plano
        plt.xlim(-abs(cord_maior) - 0.1 * cord_maior, abs(cord_maior) + 0.1 * cord_maior)
        plt.ylim(-abs(cord_maior) - 0.1 * cord_maior, abs(cord_maior) + 0.1 * cord_maior)

        # Mostra a janela
        plt.show()
        return self
