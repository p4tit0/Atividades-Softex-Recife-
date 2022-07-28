# ***Primeira atividade sobre Estruturas e variáveis***
_Aluno: Vinícius Santos Lima  E-mail: viniciussantoslima2003@gmail.com<br>Data: 28 de julho de 2022_
#  

### Enunciado: 

Pesquise sobre padrões de projeto e escolha um para apresentar e descrever o seu funcionamento. Além disso, explique quais as vantagens e desvantagens comparados a outros e mostre suas referências.<br>
<br>
**Realize essa atividade no WORD ou no Bloco de Notas, suba esse arquivo para algum repositório e compartilhe o link no campo ao lado para que outros desenvolvedores possam analisá-lo.**

<h3>Padrão escolhido: <a href="https://refactoring.guru/design-patterns/iterator">Iterador</a></h3>
Clique no hiperlink acima ou acesse "https://refactoring.guru/design-patterns/iterator" para ter uma explicação mais completa.<br>
<br>
Imagine que tenhamos uma coleção de objeto. Como podemos acessar os elementos de forma que não acessemos o mesmo elemento diversas vezes? quando esses elementos estão organizados em uma estrutura linear como uma lista isso é muito simples, basta olhar cada elemento um a um, mas quando temos uma estrutura não linear como um grafo ou uma arvore a coisa é bem maios complicada. tomando uma arvore como exemplo, podemos acessar os elementos com base no nível, na distancia ou de qualquer outra forma que for preciso. As vezes em um código é preciso acessar a mesma coleção de dados de diversas formas diferentes e implementar todas elas no código principal pode gerar diversos problemas como a redução da legibilidade do código e a poluição do mesmo.<br>
Uma solução simples para esse problema é através da criação de objetos iteradores, onde cada uma obedece uma regra específica para o acsso de dados e cuida de todos os os detalhes da navegação como a posição atual e a verificação de quantos elementos restam.
<img src="/Assets/Imagens/Estruturas e Variáveis/solution1.png" width=600>
