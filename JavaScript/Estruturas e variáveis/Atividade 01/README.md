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
Imagine que tenhamos uma coleção de objeto. Como podemos acessar os elementos de forma que não acessemos o mesmo elemento diversas vezes? quando esses elementos estão organizados em uma estrutura linear como uma lista isso é muito simples, basta olhar cada elemento um a um, mas quando temos uma estrutura não linear como um grafo ou uma arvore a coisa é bem maios complicada. tomando uma arvore como exemplo, podemos acessar os elementos com base no nível, na distancia ou de qualquer outra forma que for preciso.<br>
<img src="/Assets/Imagens/Estruturas e Variáveis/problem2.png" width=600>
As vezes em um código é preciso acessar a mesma coleção de dados de diversas formas diferentes e implementar todas elas no código principal pode gerar diversos problemas como a redução da legibilidade do código e a poluição do mesmo.<br>
<br>
Uma solução simples para esse problema é através da criação de objetos iteradores, onde cada uma obedece uma regra específica para o acsso de dados e cuida de todos os os detalhes da navegação como a posição atual e a verificação de quantos elementos restam.<br>
<img src="/Assets/Imagens/Estruturas e Variáveis/solution1.png" width=600>
A estrutura de um iterador é bem simples:

1. Interface do iterador:
   - Primero cria-se uma interface para o iterador, a qual vai definir quais métodos e propriedade aquele iterador terá.
2. Iterador concreto:
   - Após isso é preciso definir o comportamente de cada um dos métodos que o iterador possuira através da criação de uma classe concreta.
3. Interface da coleção:
   - Depois é preciso criar uma interfasse de contenção que ira listar todos os métodos que são resposáveis por regularizar a costrução do objeto iterador.
4. Coleção concreta:
   - Por fim só resta definir o comportamento de tais métodos e o objeto iterador está e o objeto iterador está preticamente pronto
5. Utlização:
   - Agora só resta utilizar. É aconselhavel que só se utilize as interfaces para manipular os iteradores, embora existam casos em qe seja peciso criar um iterador diretamente atraves das classes concretas.

<img src="/Assets/Imagens/Estruturas e Variáveis/structure.png" width=600>
segue um código de implementação de um iterador feito em java

```java
public interface Iterator {
   public boolean hasNext();
   public Object next();
}

public interface Container {
   public Iterator getIterator();
}

public class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}

public class IteratorPatternDemo {
	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 	
   }
}
```
Algumas vantages desse padrão de projeto são
- Limpeza do código:
  - Como os iteradors são definidos em classes separadas tem-se como resultado um código principal muito mais limpo e legivel.
- Fácil implementação:
  - Do jeito que o objeto iterador é estruturado torna-se possível implementar novas coleç~es e iteradores no código sem muitas dificuldades
- Multiplas iterações:
  - Como cada objeto iterador é independente, é possível iterar de diversas formas diferentes pelo mesmo objeto simultaniamente.
- Controle individual de iterações:
  - Pelo mesmo motivo descrito anteriormente é possível pausar e retomar cada iteração individualmente<br>

Por outro lado esse padrão possui algumas desvantagens, que são:<br>

- Overkill:
  - O uso de um objeto iterador pode ser um exagero quando se está trabalhando com coleções simples de dados, ao invés disso é recomendável acessar os objetos diretamente.

### Fontes:
- https://refactoring.guru/design-patterns/iterator
- https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
