# ***Regras da API REST***


### cliente-servidor
  Desde que ambos saibam o formato das mensagens que ambos podem trocar entre si, as tarefas cumpridas pelo cliente e pelo servidor devem manter-se modulares e separadas.

### Stateless (sem estado)
  O servidor não precisa saber nada sobre o estado em que o cliente se encontra e vice-versa.
  
### interface uniforme
  A API oferece uma interface uniforme entre os componentes, obtida através dos seguintes restrições :
  - identificação de recursos;
  - gerenciamento de recursos por meio de representações;
  - mensagens auto-descritivas;
  - hipermídia.
 
### armazenamento em cache
  Toda resposta enviada pelo servidor deve ser definida, diretamente ou não, como cacheáveis se necessário, permitindo que o cliente acesse essas informações novamente no futuro para realizar operações similares.
  
### sistema em camadas
  A arquitetura é constituída por camadas que seguem uma ordem hierárquica, onde cada elemento só consegue "visualizar" elementos pertencentes a ccamada a qual está interagindo.
  
### código sob demanda
  Determina a possíbilidade que um código do cliente seja baixado e usado dentro do aplicativo.
