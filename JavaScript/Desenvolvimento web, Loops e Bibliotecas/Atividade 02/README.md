# ***Segunda atividade sobre Desenvolvimento Web, Loops e Bibliotecas***
_Aluno: Vinícius Santos Lima  E-mail: viniciussantoslima2003@gmail.com<br>Data: 04 de agosto de 2022_
#  

### Enunciado: 
Crie um código JavaScript no back-end utilizando o Node.JS. No código, você deverá elaborar uma ou mais rotas, que podem ser de qualquer tipo (get, put etc). Depois, faça um teste em aplicações de rota, como o Postman ou o Insomnia, para confirmar se o retorno está coerente com o que você programou.<br>
<br>
Observação: os prints, ou o próprio código, devem ser divididos por arquivos. Por exemplo, o código de rotas está em um arquivo diferente do código de conexão.

**Trabalhe esse código em seu IDE, suba ele para sua conta no GitHub e compartilhe o link desse projeto no campo ao lado para que outros desenvolvedores possam analisá-lo.**

<h3><a href="https://github.com/p4tit0/Atividades-Softex-Recife-/blob/main/JavaScript/Desenvolvimento%20web%2C%20Loops%20e%20Bibliotecas/Atividade%2002/src/index.js">Resolução</a></h3>
Clique no hiperlink acima ou acesse o arquivo "src/index.js" para ver a resolução do exercício.<br>
Como pode ser observado no arquivo <a href="https://github.com/p4tit0/Atividades-Softex-Recife-/blob/main/JavaScript/Desenvolvimento%20web%2C%20Loops%20e%20Bibliotecas/Atividade%2002/src/conn.json">"src/conn.json"</a> foram definidas 4 rotas, 2 principais e 2 secundarias, e em cada uma, como mostra a linha 13 do código:

```js
return response.send(`Hello!<br>This is the ${route[0]} route "${subroute}".<br><img src="https://catiororeflexivo.com/wp-content/uploads/2020/07/funny-expressive-dog-corgi-genthecorgi-1-22-5f0ea744e82d4__700.jpg"  width="300">`);
```

Será exibido um pequeno texto informando o tipo (principal/secundário) e o número da rota seguido da seguinte imagem:
<a href="https://catiororeflexivo.com/wp-content/uploads/2020/07/funny-expressive-dog-corgi-genthecorgi-1-22-5f0ea744e82d4__700.jpg"><img src="https://catiororeflexivo.com/wp-content/uploads/2020/07/funny-expressive-dog-corgi-genthecorgi-1-22-5f0ea744e82d4__700.jpg"  width="600"></a><br>
Seguem as prints mostrando os resultádos dos testes:

<img src="/JavaScript/Desenvolvimento web, Loops e Bibliotecas/Atividade 02/prints/01.png"  width="960">
<img src="/JavaScript/Desenvolvimento web, Loops e Bibliotecas/Atividade 02/prints/02.png"  width="960">
<img src="/JavaScript/Desenvolvimento web, Loops e Bibliotecas/Atividade 02/prints/03.png"  width="960">
<img src="/JavaScript/Desenvolvimento web, Loops e Bibliotecas/Atividade 02/prints/04.png"  width="960">

Cada uma dessas imagens pode ser encontrada na pasta "prints"
