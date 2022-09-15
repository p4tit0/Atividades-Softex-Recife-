
package strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Implementação do padrão de projeto strategy.
 * @author Vinícius Santos Lima
 */
public class StrategyPattern {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    interface CalculatorStrategy {
        public abstract int execute(int n1, int n2);
    }
    
    static class Sum implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return n1 + n2;
        }
    }
    
    static class Subtraction implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return n1 - n2;
        }
    }
    
    static class Division implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return (int) n1 / n2;
        }
    }
    
    static class Multiplication implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return n1 * n2;
        }
    }
    
    static class Power implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return (int) Math.pow(n1, n2);
        }
    }
    
    static class Modulus implements CalculatorStrategy{
        @Override
        public int execute(int n1, int n2) {
            return n1 % n2;
        }
    }
    
    static class Calculator {
        private CalculatorStrategy operation;
        
        public void setOperation(CalculatorStrategy operation) {
            this.operation = operation;
        }
        
        public int calculate(int n1, int n2) {
            return this.operation.execute(n1, n2);
        }
    }
    
    private static int getIntRangeInput(String msg, String err_msg, int max, int... min) {
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
                java.util.logging.Logger.getLogger(StrategyPattern.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
    
    private static int getIntInput(String msg, String err_msg) {
        while (true)
        {
            System.out.print(msg);
            try {
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException ex){
                System.out.println(TextFormat.msg1(50, "ERROR", err_msg, "-", "RED", "WHITE", "ITALIC+BOLD"));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(StrategyPattern.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Logger.getLogger(StrategyPattern.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        final Calculator calc = new Calculator();
            
            
        while (true) {
            System.out.println(TextFormat.title(51, "SIMULADOR DE CALCULADORA 2.0 MKII", "-=-", "MAGENTA", "WHITE", "BOLD+ITALIC"));
            System.out.println("Bem vindo a simulador de calculadora 2.0 MKII!");
            
            int n1 = getIntInput("Insíra o primeiro operando: ", "INSIRA UM NÚMERO!");
            int n2 = getIntInput("Insíra o segundo operando: ", "INSIRA UM NÚMERO!");
            
            System.out.println("Qual operação você quer realizar?");
            System.out.println("   1 - Soma");
            System.out.println("   2 - Subtração");
            System.out.println("   3 - Multiplicação");
            System.out.println("   4 - Divisão");
            System.out.println("   5 - Potenciação");
            System.out.println("   6 - Resto\n");
            
            int opt = getIntRangeInput("Opcao escolhida: ", "INSIRA UMA OPCAO VALIDA!", 6, 1);
            
            
            String operation = "operação";
            switch (opt) {
                case 1:
                    operation = "soma";
                    calc.setOperation(new Sum());
                    break;
                case 2:
                    operation = "subtração";
                    calc.setOperation(new Subtraction());
                    break;
                case 3:
                    operation = "multiplicação";
                    calc.setOperation(new Multiplication());
                    break;
                case 4:
                    operation = "divisão";
                    calc.setOperation(new Division());
                    break;
                case 5:
                    operation = "potenciação";
                    calc.setOperation(new Power());
                    break;
                case 6:
                    calc.setOperation(new Modulus());
                    break;
            }
            
            System.out.printf("O resultado da %s é %d.\n", operation, calc.calculate(n1, n2));
            
            if (!get_bool_input("Realizar outra operação (S/N)? ", "INSIRA UMA RESPOSTA VÁLIDA", "S", "N"))
                break;
        }
        System.out.println("Até a próxima!");
    }
}
