
package serializacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Resolução do desenvolvimento 26
 * @author Vinícius Santos Lima
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        System.out.println(TextFormat.title(51, "SISTEMA DE CADASTRO", "-=-", "YELLOW", "WHITE", "ITALIC+BOLD"));
        UserList cadastros = null;
        while (true)
        {
            System.out.println("Escolha uma das seguintes opcoes:");
            System.out.println("  1 - Ver cadastros");
            System.out.println("  2 - Cadastrar usuario");
            System.out.println("  3 - Salvar cadastros");
            System.out.println("  4 - Carregar cadastros");
            System.out.println("  0 - Sair\n");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int op;
            while (true)
            {
                try 
                {
                    System.out.print("Opcao escolhida: ");
                    op = Integer.parseInt(br.readLine());
                    if (op < 0 || op > 4)
                    {
                        System.out.println(TextFormat.msg1(50, "ERROR", "Escolha uma opcao valida!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        continue;
                    }
                    break;
                }catch(NumberFormatException nfe) {
                    System.out.println(TextFormat.msg1(50, "ERROR", "Escolha uma opcao valida!", "-", "RED", "WHITE", "ITALIC+BOLD"));
                }
            }
            System.out.print("\n");
            switch(op)
            {
                case 0:
                    System.out.println("Ate a proxima!");
                    return;
                case 1:
                    if (cadastros == null)
                    {
                        System.out.println("Ainda não há cadastros");
                        System.out.print("\n");
                        continue;
                    }
                    UserList current_node = cadastros;
                    while (current_node != null)
                    {
                        System.out.println(current_node.user.get_username() + ": " + current_node.user.get_password());
                        current_node = current_node.next_node;
                    }
                    System.out.print("\n");
                    break;
                case 2:
                    System.out.print("Nome de usuário: ");
                    String nome = br.readLine();
                    System.out.print("Senha: ");
                    String senha = br.readLine();
                    
                    try{
                        if (cadastros == null)
                            cadastros = new UserList(new User(nome, senha));
                        else
                            cadastros.add_node(new User(nome, senha));
                    }catch (Exception ex){
                        System.out.println(TextFormat.msg1(50, "ERROR", ex.getMessage(), "-", "RED", "WHITE", "ITALIC+BOLD"));
                    }
                    System.out.print("\n");
                    break;
                case 3:
                    try {
                        File arq = new File("cad.dat");
                        
                        arq.delete();
                        arq.createNewFile();

                        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arq));
                        obj.writeObject(cadastros);
                        obj.close();

                    } catch (IOException ex) {
                        System.out.println(TextFormat.msg1(50, "ERROR", "Algo deu errado...", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        System.out.println(ex);
                    }
                    break;
                case 4:
                    try {
                        File arq = new File("cad.dat");
                        if  (arq.exists())
                        {
                            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(arq));
                            cadastros = (UserList) obj.readObject();
                            obj.close();
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println(TextFormat.msg1(50, "ERROR", "Algo deu errado...", "-", "RED", "WHITE", "ITALIC+BOLD"));
                        System.out.println(ex);
                    } 
                    break;

            }

        }
        
    }
    
}
