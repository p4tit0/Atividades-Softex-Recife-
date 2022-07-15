
package serializacao;

import java.io.Serializable;
/**
 * Resolução do desenvolvimento 26
 * @author Vinícius Santos Lima
 */
public class User implements Serializable
{
    private String username;
    private String password;
    
    public User(String username, String password) throws Exception 
    {
        set_username(username);
        set_password(password);
    }
    
    public void set_username(String new_username) throws Exception
    {
        if (new_username.length() > 20)
            throw new Exception("O nome de usuário deve ter menos de 20 caracteres");
        for (int i = 0; i < new_username.length(); i++)
        {
            if (!Character.isDigit(new_username.charAt(i))
                && !Character.isLetter(new_username.charAt(i))
                && new_username.charAt(i) != '-'
                && new_username.charAt(i) != '_')
                throw new Exception(String.format("O nome de usuário não pode conter \"%c\"", new_username.charAt(i)));
        }
        
        this.username = new_username;
    }
    
    public void set_password(String new_password) throws Exception
    {
        int passwordLength = 8;
        if(new_password.length() < passwordLength)
            throw new Exception(String.format("A senha deve ter no mínimo %d caracteres", passwordLength));
        
        int upChars=0, lowChars=0, special=0, digits=0;
        char ch;
        for(int i=0; i<new_password.length(); i++)
        {
            ch = new_password.charAt(i);
            if (Character.isUpperCase(ch))
                upChars++;
            else if (Character.isLowerCase(ch))
                lowChars++;
            else if (Character.isDigit(ch))
                digits++;
            else
                special++;
            
            if (upChars > 0 && lowChars > 0 && special > 0 && digits > 0) // remover para realizar calculo de força
                break;
        }
        if (upChars == 0)
            throw new Exception("A senha deve conter pelo menos uma letra maiúscula");
        else if (lowChars == 0)
            throw new Exception("A senha deve conter pelo menos uma letra minúscula");
        else if (special == 0)
            throw new Exception("A senha deve conter pelo menos um caractere especial");
        else if (digits == 0)
            throw new Exception("A senha deve conter pelo menos um dígito");

        this.password = new_password;
    }
    
    public String get_username()
    {
        return this.username;
    }
    
    public String get_password()
    {
        return this.password;
    }
}
