# ***Definição de um modelo REST***

### Create
Cadastra um novo livro no sistema
 ``` 
 POST /add-book
 ```
#### Parâmetros

| **_Nome_** | **_Tipo_** | **_In_** |                **_Descrição_**               |
|:----------:|:----------:|:--------:|:--------------------------------------------:|
| Nome       |  String    | Body     | Nome do livro que será cadastrado no sistema |
| Autor      |  String    | Body     | Autor do livro que será cadastrado           |
| ISBN       |  Int       | Body     | Código ISBN do livro cadastrado              |

#### Response
``` 
 Status:  201
 ```
 ```json
 {   
  "Nome": "No Longer Human",
  "Autor": "Osamu Dazai",
  "ISBN": 0811204812,
  "created_at": "1948-11-22",
  "update_at": "1948-11-22"
}
 ```
### Read 
Procura por um livro no sistema
 ``` 
 GET /search?nome={Nome}&autor={Autor}&isbn={ISBN}
 ```
#### Parâmetros

| **_Nome_** | **_Tipo_** | **_In_** |        **_Descrição_**       |
|:----------:|:----------:|:--------:|:----------------------------:|
| Nome       |  String    | Path     | Nome do livro buscado        |
| Autor      |  String    | Path     | Autor do livro buscado       |
| ISBN       |  Int       | Path     | Código ISBN do livro buscado |

#### Response
``` 
 Status:  200
 ```
 ```json
 {
  "total_count": 1,
  "search_result": [
     {   
      "Nome": "No Longer Human",
      "Autor": "Osamu Dazai",
      "ISBN": 0811204812,
      "created_at": "1948-11-22",
      "update_at": "1948-11-22"
    }
  ]
}
 ```

### Update 
Atualiza informações de um livro no sistema
 ``` 
 PUT /update
 ```
#### Parâmetros

| **_Nome_** | **_Tipo_** | **_In_** |           **_Descrição_**          |
|:----------:|:----------:|:--------:|:----------------------------------:|
| Nome       |  String    | Body     | Nome do livro a ser atualizado     |
| Autor      |  String    | Body     | Autor do livro que será atualizado |
| ISBN       |  Int       | Body     | Código ISBN do livro atualizado    |

#### Response
``` 
 Status:  200
 ```
 ```json
 {   
  "Nome": "No Longer Human",
  "Autor": "Osamu Dazai",
  "ISBN": 0811204812,
  "created_at": "1948-11-22",
  "update_at": "2022-12-23"
}
 ```
 
### Delete
Remove um livro do sistema
 ``` 
 PUT /remove
 ```
#### Parâmetros

| **_Nome_** | **_Tipo_** | **_In_** |    **_Descrição_**   |
|:----------:|:----------:|:--------:|:--------------------:|
| Nome       |  String    | Body     | Nome do livro        |
| Autor      |  String    | Body     | Autor do livro       |
| ISBN       |  Int       | Body     | Código ISBN do livro |

#### Response
``` 
 Status:  200
 ```
 ```json
 {   
  "Nome": "No Longer Human",
  "Autor": "Osamu Dazai",
  "ISBN": 0811204812,
  "created_at": "1948-11-22",
  "update_at": "2022-12-23",
  "deleted_at": "2022-12-23"
}
 ```
