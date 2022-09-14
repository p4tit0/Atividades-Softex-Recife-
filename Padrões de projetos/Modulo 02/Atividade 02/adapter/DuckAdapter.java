package adapter;

/**
 * Implementação do padrão de projeto adapter.
 * @author Vinícius Santos Lima
 */
public class DuckAdapter {
    interface Duck {
        public abstract void quack();
        
        public abstract void fly();
        
        public abstract String getName();
    }
    
    interface Chicken {
        public abstract void cluck();
        
        public abstract void fly();
        
        public abstract String getName();
    }
    
    class ConcreteDuck implements Duck {
        
        private final String name;
        
        public ConcreteDuck(String name) {
            this.name = name;
        }
        
        @Override
        public void quack() {
            System.out.println("QUACK!!!");
        }
        
        @Override
        public void fly(){
            System.out.printf("*%s começa a bater as asas*\n", this.name);
            System.out.printf("*%s levata voo*\n", this.name);
            System.out.printf("*%s da uma volta no ar*\n", this.name);
            System.out.printf("*%s pousa com perfeição*\n", this.name);
        }
        
        @Override
        public String getName() {
            return this.name;
        }
    }
    
    class ConcreteChicken implements Chicken {
        
        private final String name;
        
        public ConcreteChicken(String name) {
            this.name = name;
        }
        
        @Override
        public void cluck() {
            System.out.println("CÓ-CÓ!!!");
        }
        
        @Override
        public void fly() {
            System.out.printf("*%s começa a bater as asas*\n", this.name);
            System.out.printf("*%s continua batendo as asas*\n", this.name);
            System.out.printf("*%s parece cansado(a)*\n", this.name);
            System.out.printf("*%s cai no chão ofegante*\n", this.name);
            System.out.printf("*%s se levanta*\n", this.name);
            System.out.printf("*%s parece triste*\n", this.name);
        }
        
        @Override
        public String getName() {
            return this.name;
        }
    }
    
    class ChickenToDuckAdapter implements Duck {
        private final Chicken chicken;
        
        public ChickenToDuckAdapter(Chicken chicken) {
            this.chicken = chicken;
        }
        
        @Override
        public void quack(){
            this.chicken.cluck();
        }
        
        @Override
        public void fly(){
            this.chicken.fly();
        }
        
        @Override
        public String getName() {
            return this.chicken.getName();
        }
    }
    
    class DuckToChickenAdapter implements Chicken {
        private final Duck duck;
        
        public DuckToChickenAdapter(Duck duck) {
            this.duck = duck;
        }
        
        @Override
        public void cluck(){
            this.duck.quack();
        }
        
        @Override
        public void fly(){
            this.duck.fly();
        }
        
        @Override
        public String getName() {
            return this.duck.getName();
        }
    }
}
