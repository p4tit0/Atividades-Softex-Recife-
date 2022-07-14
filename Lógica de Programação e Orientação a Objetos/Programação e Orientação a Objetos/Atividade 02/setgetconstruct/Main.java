/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setgetconstruct;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno 8
 */
public class Main {
    public static void main(String[] args)
    {
      Pessoa p1;
        try {
            p1 = new Pessoa("Pedro", 19);
            System.out.println("Nome: "+ p1.get_nome());
            System.out.println("Idade: "+ p1.get_idade());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
