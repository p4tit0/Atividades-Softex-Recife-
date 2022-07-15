
package exceção;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Resolução do desenvolvimento 27
 * @author Vinícius Santos Lima
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println(TextFormat.title(51, "TRATAMENTO DE EXCECOES", "-=-", "MAGENTA", "WHITE", "ITALIC+BOLD"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean trouxa = false;
        while (true)
        {
            if (!trouxa)
                System.out.print("Digite uma letra para quebrar o loop: ");
            else
                System.out.print("Digite um numero para quebrar o loop: ");
            try{
                Integer.parseInt(br.readLine());
                break;
            }catch(NumberFormatException ex){
                if (!trouxa)
                    System.out.println(TextFormat.msg1(50, "ERROR", "Eu menti!!! era para você digitar um numero >:D", "-", "RED", "WHITE", "ITALIC+BOLD"));
                else
                    System.out.println(TextFormat.msg1(50, "ERROR", "Insira um numero!!!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                System.out.println(ex.getMessage());
                trouxa = true;
            }
            
        }
        System.out.println(TextFormat.msg1(50, "PARABENS!", "Voce quebrou o loop", "-", "GREEN", "WHITE", "ITALIC+BOLD"));
    }
    
}
