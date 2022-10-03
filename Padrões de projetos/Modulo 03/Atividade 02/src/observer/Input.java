
package observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para tratar o input de usuários
 * @author Vinícius Santos Lima
 */
public class Input {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public String get_str_input(String msg) {
        try {
            System.out.print(msg);
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int get_int_range_input(String msg, String err_msg, int max, int... min) {
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
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int get_positive_input(String msg, String err_msg, int... min) {
        int min_val = min.length > 0 ? min[0] : 0;
        while (true)
        {
            System.out.print(msg);
            try {
                int input = Integer.parseInt(br.readLine());
                if (input < min_val) {
                    System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
                    continue;
                }
                return input;
            } catch (NumberFormatException ex){
                System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
            } catch (IOException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean get_bool_input(String msg, String err_msg, String true_opt, String false_opt)
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
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
}
