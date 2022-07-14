
package setgetconstruct;

/**
 * Resolução do Desenvolvimento 23.
 * @author Vinícius Santos Lima
 */
public class Pessoa 
{
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) throws Exception
    {
        this.nome = nome;
        set_idade(idade);
    }

    public String get_nome()
    {
        return this.nome;
    }

    public int get_idade()
    {
        return this.idade;
    }

    public void set_nome(String new_nome)
    {
        this.nome = new_nome;
    }

    public void set_idade(int new_idade) throws Exception
    {
        if (0 > new_idade || new_idade > 120)
            throw new Exception("Idade inválida");
        this.idade = new_idade;
    }
}

