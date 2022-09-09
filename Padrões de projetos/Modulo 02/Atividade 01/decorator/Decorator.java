/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package decorator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author space
 */

abstract class Sandwich
{
    protected String description = "unknown";
    protected double price = 0;

    public String getDescription()
    {
        return this.description;
    }

    public abstract double getPrice();
}

class VegetarianSandwich extends Sandwich
{
    public VegetarianSandwich()
    {
        this.description = "Sanduíche vegetariano";
        this.price = (double) 8.5;
    }

    @Override
    public double getPrice()
    {
        return (double) this.price;
    }
}

class ChickenSandwich extends Sandwich
{
    public ChickenSandwich()
    {
        this.description = "Sanduíche de frango assado";
        this.price = (double) 4.5;
    }

    @Override
    public double getPrice()
    {
        return (double) this.price;
    }
}

class MeatSandwich extends Sandwich
{
    public MeatSandwich()
    {
        this.description = "Sanduíche de carne";
        this.price = (double) 4.0;
    }

    @Override
    public double getPrice()
    {
        return (double) this.price;
    }
}

abstract class IngredientDecorator extends Sandwich
{
    protected String description = "unknown";
    protected double price = 0;
    protected Sandwich sandwich;

    public IngredientDecorator(Sandwich sandwich)
    {
        this.sandwich = sandwich;
    }

    public abstract String getDescription();

    public abstract double getPrice();
}


class MozzarellaCheese extends IngredientDecorator
{
    public MozzarellaCheese(Sandwich sandwich)
    {
        super(sandwich);
        this.description = "Queijo mussarela ralado";
        this.price = (double) 2.0;
    }

    @Override
    public double getPrice()
    {
        return this.sandwich.getPrice() + this.price;
    }

    @Override
    public String getDescription()
    {
        return this.sandwich.getDescription() + " + " + this.description;
    }
}


class Pepperoni  extends IngredientDecorator
{
    public Pepperoni(Sandwich sandwich)
    {
        super(sandwich);
        this.description = "Pepperoni";
        this.price = (double) 0.99;
    }

    @Override
    public double getPrice()
    {
        return this.sandwich.getPrice() + this.price;
    }

    @Override
    public String getDescription()
    {
        return this.sandwich.getDescription() + " + " + this.description;
    }
}
    
    
class Bacon extends IngredientDecorator
{
    public Bacon(Sandwich sandwich)
    {
        super(sandwich);
        this.description = "Bacon";
        this.price = (double) 1.49;
    }

    @Override
    public double getPrice()
    {
        return this.sandwich.getPrice() + this.price;
    }

    @Override
    public String getDescription()
    {
        return this.sandwich.getDescription() + " + " + this.description;
    }
}
    

public class Decorator {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
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
                Logger.getLogger(Decorator.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Decorator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) 
    {
        System.out.println(TextFormat.title(51, "SUBWAY", "-=-", "GREEN", "WHITE", "BOLD+ITALIC"));
        System.out.println("Bem vindo ao atendimento online da subway!");
        
        Sandwich sandwich = null;
        while (true)
        {                        
            System.out.println("Que sanduíche você quer?");
            System.out.println("   1 - Sanduíche de carne");
            System.out.println("   2 - Sanduíche de frango");
            System.out.println("   3 - Sanduíche vegetariano");
            System.out.println("   0 - Sair\n");
            
            int op = get_int_range_input("Opcao escolhida: ", "INSIRA UMA OPCAO VALIDA!", 3);
            
            switch (op)
            {
                case 0:
                    System.out.println("Até a próxima!");
                    System.exit(0);
                    break;
                case 1:
                    sandwich = new MeatSandwich();
                    break;
                case 2:
                    sandwich = new ChickenSandwich();
                    break;
                case 3:
                    sandwich = new VegetarianSandwich();
                    break;
            }
            
            while (get_bool_input("Deseja acrescentar algum ingrediente? (S/N) ", "INSIRA UMA OPCAO VALIDA!", "S", "N"))
            {
                System.out.println("Que sanduíche adicionar ao sanduíche?");
                System.out.println("   1 - Queijo mussarela ralado");
                System.out.println("   2 - Pepperoni");
                System.out.println("   3 - bacon");
                
                op = get_int_range_input("Opcao escolhida: ", "INSIRA UMA OPCAO VALIDA!", 3, 1);
                
                switch (op)
                {
                    case 1:
                        sandwich = new MozzarellaCheese(sandwich);
                        break;
                    case 2:
                        sandwich = new Pepperoni(sandwich);
                        break;
                    case 3:
                        sandwich = new Bacon(sandwich);
                        break;
                }
            }
            
            System.out.println("O valor total da sua conta é: ");
            System.out.println(sandwich.getDescription() + " = R$" + sandwich.getPrice());
            
            if (!get_bool_input("Quer fazer outro sanduíche? (S/N) ", "INSIRA UMA OPCAO VALIDA!", "S", "N"))
                break;
        }
    }
    
}
