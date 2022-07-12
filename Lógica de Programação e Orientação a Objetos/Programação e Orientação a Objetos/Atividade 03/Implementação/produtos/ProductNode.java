
package produtos;

/**
 *
 * @author space
 */
public class ProductNode 
{
    Produto produto;
    ProductNode next_node;

    public ProductNode(Produto produto)
    {
        this.produto = produto;
        this.next_node = null;
    }
    
    public ProductNode get_node(int idx) throws Exception
    {
        if (idx < 0)
            throw new Exception("O índice deve ser positivo");
        
        ProductNode current_node = this;
        int current_idx = 0;
      
        while (current_node.next_node != null && current_idx < idx)
        {
            current_node = current_node.next_node;
            current_idx++;
        }
        if (current_idx < idx)
            throw new Exception("Índice fora de alcance");
        return current_node;
    }
    
    public void add_node(Produto produto)
    {
        ProductNode current_node = this;
        while (current_node.next_node != null)
            current_node = current_node.next_node;
        current_node.next_node = new ProductNode(produto);
    }
}
