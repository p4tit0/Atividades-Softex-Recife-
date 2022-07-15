/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializacao;

import java.io.Serializable;

/**
 * Resolução do desenvolvimento 26
 * @author Vinícius Santos Lima
 */
public class UserList implements Serializable 
{
    User user;
    UserList next_node;

    public UserList(User produto)
    {
        this.user = produto;
        this.next_node = null;
    }
    
    public UserList get_node(int idx) throws Exception
    {
        if (idx < 0)
            throw new Exception("O índice deve ser positivo");
        
        UserList current_node = this;
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
    
    public void add_node(User produto)
    {
        UserList current_node = this;
        while (current_node.next_node != null)
            current_node = current_node.next_node;
        current_node.next_node = new UserList(produto);
    }
}
