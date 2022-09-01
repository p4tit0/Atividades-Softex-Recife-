/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno 8
 */
public class Aplication {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static String get_str_input(String msg)
    {
        System.out.print(msg);
        try {
            return br.readLine().strip();
        } catch (IOException ex) {
            Logger.getLogger(Aplication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private static int get_int_range_input(String msg, String err_msg, int max, int... min)
    {
        int min_val = min.length > 0 ? min[0] : 0;
        while (true)
        {
            System.out.print(msg);
            try {
                int input = Integer.parseInt(br.readLine());
                if (input < min_val || input > max) {
                    System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
                    continue;
                }
                return input;
            } catch (NumberFormatException ex){
                System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
            } catch (IOException ex) {
                Logger.getLogger(Aplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void main(String[] args) {
        Prototype.Veicle[] inventory = new Prototype.Veicle[6];
        int inventory_idx = 0;
        
        System.out.println(TextFormat.title(51, "FABRICA DE CARROS", "-=-", "CYAN", "WHITE", "BOLD+ITALIC"));
        System.out.println("Bem vindo a fabrica de carros!");
        while (true)
        {
            System.out.println("O que você deseja fazer?");
            System.out.println("   1 - Criar um carro");
            System.out.println("   2 - Criar uma moto");
            System.out.println("   3 - Copiar o ultimo veiculo");
            System.out.println("   4 - Preencher o restante do estoque");
            System.out.println("   5 - Verificar estoque");
            System.out.println("   0 - Sair\n");
            
            int op = get_int_range_input("Opcao escolhida: ", "INSIRA UMA OPCAO VALIDA!", 5);
            
            switch (op)
            {
                case 0:
                    System.out.println("Até a próxima!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Insira as informações do carro que você deseja criar.");
                    String brand = get_str_input("Qual a marca? ");
                    String model = get_str_input("Qual o modelo? ");
                    String wheels_num = get_str_input("Tem quantas rodass? ");
                    String color = get_str_input("Qual a cor? ");
                    
            }
        }
        
    }
}
