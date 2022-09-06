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
    private static Prototype prototype = new Prototype();
    
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
    
    private static int get_int_input(String msg, String err_msg)
    {
        while (true)
        {
            System.out.print(msg);
            try {
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException ex){
                System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
            } catch (IOException ex) {
                Logger.getLogger(Aplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static boolean get_bool_input(String msg, String err_msg, String true_opt, String false_opt)
    {
        while (true)
        {
            System.out.print(msg);
            String input;
            try {
                input = br.readLine().toUpperCase();
                if (input.equals(true_opt.toUpperCase()))
                    return true;
                else if (input.equals(false_opt.toUpperCase()))
                    return false;
                else
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
                    if (inventory_idx >= 6){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE JA ESTA CHEIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    }
                    System.out.println("Insira as informações do carro que você deseja criar.");
                    String brand = get_str_input("Qual a marca? ");
                    String model = get_str_input("Qual o modelo? ");
                    int wheels_num = get_int_input("Tem quantas rodas? ", "INSIRA UM NÚMERO VALIDO!");
                    String color = get_str_input("Qual a cor? ");
                    int doors_num = get_int_input("Tem quantas portas? ", "INSIRA UM NÚMERO VALIDO!");
                    boolean is_automatic = get_bool_input("Tem cambio automatico? (S/N)", "INSIRA UMA OPCAO VALIDA", "S", "N");
                    
                    inventory[inventory_idx] = prototype.new Car(model, brand, color, wheels_num, doors_num, is_automatic);
                    inventory_idx += 1;
                    break;
                case 2:
                    if (inventory_idx >= 6){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE JA ESTA CHEIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    }
                    System.out.println("Insira as informações do carro que você deseja criar.");
                    brand = get_str_input("Qual a marca? ");
                    model = get_str_input("Qual o modelo? ");
                    wheels_num = get_int_input("Tem quantas rodas? ", "INSIRA UM NÚMERO VALIDO!");
                    color = get_str_input("Qual a cor? ");
                    int engine_capacity = get_int_input("Tem quantas cilindradas? ", "INSIRA UM NÚMERO VALIDO!");
                    boolean has_sidecar = get_bool_input("Tem sidecar? (S/N)", "INSIRA UMA OPCAO VALIDA", "S", "N");
                    
                    inventory[inventory_idx] = prototype.new Motorcycle(model, brand, color, wheels_num, engine_capacity, has_sidecar);
                    inventory_idx += 1;
                    break;
                case 3:
                    if (inventory_idx >= 6){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE JA ESTA CHEIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    } else if (inventory_idx == 0){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE ESTA VAZIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    }
                    inventory[inventory_idx] = inventory[inventory_idx - 1].getClone();
                    inventory_idx += 1;
                    break;
                case 4:
                    if (inventory_idx >= 6){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE JA ESTA CHEIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    } else if (inventory_idx == 0){
                        System.out.println(TextFormat.msg1(50, "ERROR", "O ESTOQUE ESTA VAZIO!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    }
                    while(inventory_idx < 6)
                    {
                        inventory[inventory_idx] = inventory[inventory_idx - 1].getClone();
                        inventory_idx += 1;
                    }
                    break;
                case 5:
                    for(int i = 0; i < inventory_idx; i++){
                        System.out.printf("------------ %02d ------------", i+1);
                        System.out.println(inventory[i].getInfo());
                    }
                    break;
                        
            }
        }
        
    }
}