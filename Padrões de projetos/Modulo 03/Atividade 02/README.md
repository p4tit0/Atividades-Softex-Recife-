# ***Segunda atividade do terceiro módulo de padrões de desenvolvimento***
_Aluno: Vinícius Santos Lima  E-mail: viniciussantoslima2003@gmail.com<br>Data: 16 de setembro de 2022_
#  

### Enunciado: 
Aplique o padrão de projeto Observer para criar um simples editor de texto. Considere a solução apresentada no Hipertexto 6 e utilize o código visto para implementar novas classes. Os requisitos para avaliar o projeto são:
<br>
- implementar uma subclasse da classe Editor chamada TextEditor;
- aplicar o método insertLine, que recebe os parâmetros lineNumber e text;
- inserir o texto na ordem correspondente a lineNumber e deslocar as linhas posteriores se necessário;
- aplicar o método removeLine, que recebe lineNumber como parâmetro, deleta o texto da linha correspondente e desloca as linhas posteriores se necessário;
- instanciar um TextEditor e disparar o evento “open”;
- receber as linhas de texto, que serão inseridas no objeto textEditor, do usuário até que ele envie o texto “EOF”, que não deve ser inserido no objeto textEditor;
- por fim, o textEditor deve disparar o evento “save” para que o conteúdo seja salvo no arquivo configurado e imprimir todo o conteúdo do arquivo na tela.

**Trabalhe esse código em seu IDE, suba ele para sua conta no GitHub e compartilhe o link desse projeto no campo ao lado para que outros desenvolvedores possam analisá-lo.**

<h3><a href="https://github.com/p4tit0/Atividades-Softex-Recife-/blob/main/Padrões%20de%20projetos/Modulo%2003/Atividade%2002/src/observer/ObserverPattern.java">Resolução</a></h3>
Clique no hiperlink acima ou acesse o arquivo "src/observer/ObserverPattern.java" para ver a resolução do exercício.<br>
