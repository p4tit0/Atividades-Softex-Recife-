#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include<time.h>

#define INIT_DECK_SIZE 22 

/**
 * Função: limparBufferDeEntrada
 * ----------------------------
 *   Elimina quaisquer caracteres deixados no buffer de entrada deixados por outras funções
 *
 *   retorno: nenhum 
 */
void limparBufferDeEntrada(void){
    int c;
    while ((c = getchar()) != '\n' && c != EOF) { }
}

/**
 * Função: mostrarVetor
 * ----------------------------
 *   Mostra o estado atual do vetor
 *
 *   vetor: vetor que sera exibido 
 *   tamanho: tamanho do vetor
 *
 *   retorno: nenhum 
 */
void mostrarVetor(int vetor[], int tamanho)
{
    printf("[ ");
    for (int i = 0; i < tamanho; i++)
    {
        printf("%i", vetor[i]);
        
        if (i < tamanho - 1) 
            printf(", "); // escreve ", " caso não seja o último elemento do vetor
    }
    printf(" ]\n");
}


/**
 * Função: somarPontos
 * ----------------------------
 *   Calcula a soma de todos os elementos o vetor
 *
 *   vetor: vetor que sera exibido 
 *   tamanho: tamanho do vetor
 *
 *   retorno: (inteiro) soma dos elementos do vetor 
 */
int somarPontos(int vetor[], int tamanho)
{
    int sum = 0;
    for (int i = 1; i < tamanho; i++)
        sum += vetor[i];
    return sum;
}


int main(void) 
{
    srand(time(0));
    
    int exit = 0;
    while(!exit)
    {
        printf("\n");
        printf("-=-=-=-=-=- BlackJack %d -=-=-=-=-=-\n", 13 * INIT_DECK_SIZE);
        printf("Bem-vindo(a) ao Black Jack %d simulator!\n", 13 * INIT_DECK_SIZE);
        printf("Escolha uma das seguintes opções:\n");
        printf("1 - Jogar\n");
        printf("2 - Regras\n");
        printf("3 - Sair\n");
        printf("O que você quer fazer? ");
        int input = 0;
        scanf(" %i", &input);
        limparBufferDeEntrada();
    
        switch(input)
        {
            case 1:
                printf("\n");
                printf("-=-=-=-=-=- BlackJack %d -=-=-=-=-=-\n", 13 * INIT_DECK_SIZE);

                int *deck = malloc(sizeof(int) * INIT_DECK_SIZE); // ✓ - use a função sizeof | ✓ - que tenha tamanho 22 de vetor
                int dec_size = INIT_DECK_SIZE;
                
                printf("Seu baralho: ");
                printf("[ ");
                for (int i = 0; i < INIT_DECK_SIZE; i++)
                {
                    deck[i] = (rand() % 13) + 1;
                    printf("%i", deck[i]);
        
                    if (i < INIT_DECK_SIZE - 1) 
                        printf(", ");
                }
                printf(" ]\n");
                printf("Pontos: %d\n", somarPontos(deck, INIT_DECK_SIZE));
                
                int gameOver = 0;
                while(!gameOver)
                {
                    printf("\n");
                    printf("Acrescentar mais uma carta ao baralho (S/N)?");
                    char more_cards;
                    scanf(" %c", &more_cards);

                    switch (toupper(more_cards))
                    {
                        case 'S':
                            dec_size++;
                            deck = realloc(deck, dec_size*sizeof(int)); // ✓ - use a função realloc
                            deck[dec_size - 1] = (rand() % 13) + 1;
                            int pontos = somarPontos(deck, dec_size);
                            if (pontos > 13 * INIT_DECK_SIZE) 
                            {
                                printf("\e[41;30m-=-=-=-=-=-=-= Perdeu! =-=-=-=-=-=-=-\e[m\n");
                                printf("\e[41;30m        Você fez %03d pontos          \e[m\n", pontos);
                                printf("\e[41;30m-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\e[m\n");
                                gameOver = 1;
                            }
                            else if (pontos < 13 * INIT_DECK_SIZE)
                            {
                                printf("Seu baralho: ");
                                mostrarVetor(deck, dec_size);
                                printf("Pontos: %d\n", pontos);
                            }
                            else
                            {
                                printf("\e[42m-=-=-=-=-=-=- Parabéns! -=-=-=-=-=-=-\e[m\n");
                                printf("\e[42m        Você fez %03d pontos          \e[m\n", pontos);
                                printf("\e[42m-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\e[m\n");
                                gameOver = 1;
                            }
                            break;
                        case 'N':
                            printf("\e[42m-=-=-=-=-=-=- Parabéns! -=-=-=-=-=-=-\e[m\n");
                            printf("\e[42m        Você fez %03d pontos          \e[m\n", somarPontos(deck, INIT_DECK_SIZE));
                            printf("\e[42m-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\e[m\n");
                            gameOver = 1;
                            break;
                        default:
                            printf("\n");
                            printf("\e[41;30m-=-=-=-=-=-=-=- Error -=-=-=-=-=-=-=-\e[m\n");
                            printf("\e[41;30m      Escolha uma opção válida!      \e[m\n");
                            printf("\e[41;30m-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\e[m\n");
                            break;
                    }
                }
                
                free(deck); // ✓ - libere o bloco utilizando a função free
                deck = NULL;
                break;
            case 2:
                printf("\n");
                printf("-=-=-=-=-=-=-=- Regras -=-=-=-=-=-=-=-\n");
                printf("As regras são simples. Você receberá um\n");
                printf("baralho de %d cartas, o valor de cada\n", INIT_DECK_SIZE);
                printf("carta pode variar entre 1 e 13. A cada\n");
                printf("rodada você tera a opção de acrescentar\n");
                printf("uma carta aleatória ao seu deck, seu \n");
                printf("objetivo é tentar chegar o mais próximo \n");
                printf("possível de 286 pontos, porém se você\n");
                printf("passar disso você perde.");
                printf("\n");
                printf("Aperte [ENTER] para voltar para o menu\n");
                while(getc(stdin)!='\n');
                break;
            case 3:
                printf("\n");
                printf("Até a próxima!\n");
                exit = 1;
                break;
            default:
                printf("\n");
                printf("\e[41;30m-=-=-=-=-=-=-=- Error -=-=-=-=-=-=-=-\e[m\n");
                printf("\e[41;30m      Escolha uma opção válida!      \e[m\n");
                printf("\e[41;30m-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\e[m\n");
                break;
        }
    }
    return 0;
}
