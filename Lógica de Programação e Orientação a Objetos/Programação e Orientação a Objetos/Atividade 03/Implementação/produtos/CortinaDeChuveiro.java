
package produtos;

/**
 *
 * @author space
 */
public class CortinaDeChuveiro extends Produto
{
    private String cor;
    private float altura;
    private float largura;
    
    public CortinaDeChuveiro(String codigo, String nome, float preco, int quant_disp, String cor, float altura, float largura) throws Exception
    {
        super(codigo, nome, preco, quant_disp);
        this.cor = cor;
        set_altura(altura);
        set_altura(largura);
    }
    
    private void set_altura(float new_alt) throws Exception
    {
        if (new_alt < 0)
            throw new Exception("A altura do produto deve ser um valor positivo");
        this.altura = new_alt;
    }
    
    private void set_largura(float new_larg) throws Exception
    {
        if (new_larg < 0)
            throw new Exception("A largura do produto deve ser um valor positivo");
        this.largura = new_larg;
    }
    
    public String get_color()
    {
        return this.cor;
    }
    
    public float get_altura()
    {
        return this.altura;
    }
    
    public float get_largura()
    {
        return this.largura;
    }
}
