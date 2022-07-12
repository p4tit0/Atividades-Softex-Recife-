
package funcionarios;

/**
 * Classe base para todos os funcionários da empresa
 * @author Vinícius Santos Lima
 */
public class Pessoa 
{
    private String nome;
    private int idade;
    private float salario;
    
    public Pessoa(String nome, int idade, float salario) throws Exception
    {
        set_idade(idade);
        set_salario(salario);
        this.nome = nome;
    }
    
    public void set_idade(int idade) throws Exception
    {
        if (0 > idade || idade > 120 )
            throw new Exception("Idade inválida");
        this.idade = idade;
    }
    
    public void set_salario(float salario) throws Exception
    {
        if (salario < 0)
            throw new Exception("O salário deve ser um número positivo");
        this.salario = salario;
    }
    
    public String get_nome()
    {
        return this.nome;
    }
    
    public int get_idade()
    {
        return this.idade;
    }
    
    public float get_salario()
    {
        return this.salario;
    }
    
}
