# Métodos HTTP

### GET:
O método `GET` solicita algum um recurso específico do servidor, pode ser um arquivo html, xml, json, etc. Requisições utilizando o método `GET` devem retornar apenas dados.

### HEAD:
O método `HEAD` solicita algum uma resposta de maneira semelhante ao `GET`, porém somente o cabeçalho dela.

### POST:
O `POST` é utilizado para enviar dados ao servidor criando um novo recurso.

### PUT:
Já o `PUT` é utilizado para atualizar recursos já existentes, substituindo todas as atuais representações do recurso de destino pela carga de dados da requisição.

### DELETE:
O método `DELETE` é responsável por remover um determinado recurso.

### TRACE:
O `TRACE` Funciona bem mais como uma ferramenta de debug para as API ‘s. Ele reenvia a última requisição para o servidor e verifica se houve alguma mudança e/ou adições de servidores de terceiros.

### OPTIONS:
O método `OPTIONS` retorna os métodos HTTP suportados pelo servidor para a URL especificada.

### CONNECT:
O método `CONNECT` estabelece um túnel para o servidor identificado pelo recurso de destino.

# WSDL
WSDL significa Web Services Description Language (Linguagem de Descrição de Web Services). Como o próprio nome já diz, WSDL é um documento (escrito em XML) utilizada para oferecer uma descrição de algum web service, por meio dele o cliente pode obter informações de como cada serviço em um end-poiint deve ser chamado, quais os parâmertros e tipos de dados esperados e qual o tipo de dado do retorno será enviado como resposta.
