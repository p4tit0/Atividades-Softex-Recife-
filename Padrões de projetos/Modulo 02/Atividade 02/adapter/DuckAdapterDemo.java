package adapter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Implementação do padrão de projeto adapter.
 * @author Vinícius Santos Lima
 */
public class DuckAdapterDemo {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int getRandInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    private static int[] getRandIntList(int length, int min, int max) {
        if (max - min + 1 < length)
            throw new IllegalArgumentException("O quantidade de números disponíveis deve ser maior ou igual ao tamanho da lista");

        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < length) {
           set.add(getRandInt(min, max));
        }
        
        int i = 0;
        int[] arr = new int[length];
        for (int e : set) {
            arr[i] = e;
            i++;
        }
        
        return arr;
    }
    
    private static int get_int_range_input(String msg, String err_msg, int max, int... min) {
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
                Logger.getLogger(DuckAdapterDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static String generateMsg(String... msg_lines) {
        String msg = "";
        for (String line : msg_lines) {
            msg += line + "\n";
        }
        return msg;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        final int chicken_num = 10;
        
        String[] duck_names = {
            "Carlos Magno",
            "César",
            "Capitão",
            "Franklin",
            "Almirante",
            "Kennedy",
            "Lloyd",
            "Wilma",
            "Timão",
            "Alfred",
        };
        
        String[] chicken_names = {
            "Canjica",
            "Salmonella",
            "Pedrita",
            "Caipira",
            "Gertrudes",
            "Amarela",
            "Moela",
            "Pipoca",
            "Tibúrcia",
            "Clotilde",
        };
        DuckAdapter duck_adapter = new DuckAdapter();
        
        int[] name_idx_arr = getRandIntList(chicken_num, 0, chicken_num - 1);
        
        DuckAdapter.Chicken[] chicken_arr = new DuckAdapter.Chicken[chicken_num];
        for (int i = 0; i < chicken_arr.length; i++) 
            chicken_arr[i] = duck_adapter.new ConcreteChicken(chicken_names[name_idx_arr[i]]);
        
        int[] pos_idx_arr = getRandIntList(getRandInt(1, 5), 0, chicken_num - 1);
        name_idx_arr = getRandIntList(pos_idx_arr.length, 0, chicken_num - 1);
        
        for (int i = 0; i < pos_idx_arr.length; i++) {
            chicken_arr[pos_idx_arr[i]] = duck_adapter.new DuckToChickenAdapter(duck_adapter.new ConcreteDuck(duck_names[name_idx_arr[i]]));
        }
        
        int duck_num = 0;
        int chick_num = 0;
        
        while (true)
        {                        
            System.out.println(TextFormat.title(51, "AMONG CHICKENS", "-=-", "YELLOW", "WHITE", "BOLD+ITALIC"));
            System.out.println("Bem vindo ao among chickens!");
            System.out.println("   1 - Investigar as galinhas");
            System.out.println("   2 - Acusar uma galinha");
            System.out.println("   3 - Encerrar o caso");
            System.out.println("   4 - Ver regras");
            System.out.println("   0 - Sair\n");
            
            int op = get_int_range_input("Opcao escolhida: ", "INSIRA UMA OPCAO VALIDA!", 4);
            
            switch (op)
            {
                case 0:
                    System.out.println("Até a próxima!");
                    System.exit(0);
                    break;
                case 1:
                    while (true) {
                        System.out.println("Qual galinha você quer investigar?");
                        for (int i = 0; i < chicken_arr.length; i++) {
                            System.out.printf("%d - %s\n", i+1, chicken_arr[i].getName());
                        }
                        System.out.println("0 - Voltar");
                        
                        op = get_int_range_input("Galinha escolhida: ", "INSIRA UMA OPCAO VALIDA!", chicken_arr.length);

                        if (op == 0)
                            break;
                        
                        DuckAdapter.Chicken chick = chicken_arr[op-1];
                        
                        System.out.printf("O que %s deve fazer?\n", chick.getName());
                        System.out.println("1 - Cacarejar");
                        System.out.println("2 - Voar");
                        op = get_int_range_input("Ação escolhida: ", "INSIRA UMA OPCAO VALIDA!", 2, 1);
                        
                        switch(op){
                            case 1:
                                
                                System.out.printf("\n%s - ", chick.getName());
                                chick.cluck();
                                System.out.println("");
                                break;
                            case 2:
                                System.out.println("");
                                chick.fly();
                                System.out.println("");
                                break;
                        }
                            
                    }
                    break;
                case 2:
                    if (chicken_arr.length <= 0) {
                        System.out.println(TextFormat.msg1(50, "ERROR", "TODAS AS GALINHAS FORAM ELIMINADAS!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                    }
                    while (true) {
                        System.out.println("Quem você acha que é o impostor?");
                        for (int i = 0; i < chicken_arr.length; i++) {
                            System.out.printf("%d - %s\n", i+1, chicken_arr[i].getName());
                        }
                        System.out.println("0 - Voltar");
                        
                        op = get_int_range_input("Galinha escolhida: ", "INSIRA UMA OPCAO VALIDA!", chicken_arr.length);

                        if (op == 0)
                            break;
                        
                        DuckAdapter.Chicken chick = chicken_arr[op-1];
                        
                        if (chick instanceof DuckAdapter.DuckToChickenAdapter)
                            duck_num++;
                        else
                            chick_num++;
                        
                        DuckAdapter.Chicken[] new_chicken_arr = new DuckAdapter.Chicken[chicken_arr.length - 1];
                        
                        for (int i = 0; i < new_chicken_arr.length; i++) {
                            if (i >= op-1)
                                new_chicken_arr[i] = chicken_arr[i+1];
                            else
                                new_chicken_arr[i] = chicken_arr[i];
                        }
                        
                        chicken_arr = new_chicken_arr;
                        
                        System.out.printf("%s virou nugget\n", chick.getName());
                    }
                    break;
                case 3:
                    System.out.println("Caso encerrado");
                    System.out.printf("Você matou %d pato(s) e %d galinha(s).\n", duck_num, chick_num);
                    System.out.println("Até a próxima!");
                    System.exit(0);
                    break;
                case 4:
                    String rules = generateMsg(
                            "Você é o investigador particular da Sadia que foi",
                            "contratado para investigar o aparecimento misterioso",
                            "de algumas galinhas \"diferenciadas\" no granjeiro.",
                            "Não se sabe quantos impostores estão entre as galinhas",
                            "e é quase impossível diferenciar as galinhas verdadeiras",
                            "das falsas pela aparência, mas todas as galnhas são bem",
                            "treinadas e irão cacarejar e voar ao seu comando, use",
                            "isso para achar as \"galinhas\" que se comportam de maneira",
                            "estranha.",
                            "Ache todos os impostores, mas cuidado para não acusar",
                            "uma galinha de verdade."
                    );
                    System.out.println(rules);
                    break;
            }
        }

    }
    
}
