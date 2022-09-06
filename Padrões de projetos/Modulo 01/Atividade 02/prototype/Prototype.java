package prototype;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação do padrão de projeto prototype.
 * @author Vinícius Santos Lima
 */

public class Prototype
{

    public interface PrototypeInterface
    {
        public PrototypeInterface getClone();
    }
    
    public abstract class Veicle implements PrototypeInterface
    {
        private String model;
        private String brand;
        private String color;
        private int wheels_num;
        
        public Veicle(String model, String brand, String color, int wheels_num)
        {
            this.model = model;
            this.brand = brand;
            this.color = color;
            this.wheels_num = wheels_num;
        }
        
        public String getModel()
        {
            return this.model;
        }
        
        public String getBrand()
        {
            return this.brand;
        }
        
        public String getColor()
        {
            return this.color;
        }
        
        public int getWheelsNum()
        {
            return this.wheels_num;
        }
        
        public void changeModel(String n_model)
        {
            this.model = n_model.strip().toUpperCase();
        }
        public void changeBrand(String n_brand)
        {
            this.brand = n_brand.strip().toUpperCase();
        }
        public void changeColor(String n_color)
        {
            this.color = n_color.strip().toUpperCase();
        }
        public void changeWheelsNum(int num)
        {
            if (num < 0)
                throw new IllegalArgumentException("O número de rodas deve ser positivo");
            this.wheels_num = num;
        }
        
        public String getInfo()
        {
            String msg = "MODEL: " + this.model + "\n";
            msg += "BRAND: " + this.brand + "\n";
            msg += "COLOR: " + this.color + "\n";
            msg += "NUMBER OF WHEELS: " + this.wheels_num;
            
            return msg;
        }
        
        @Override
        public abstract Veicle getClone();
        
    }
    
    public class Car extends Veicle
    {
        public int doors_num;
        public boolean is_automatic;
        public Car(String model, String brand, String color, int wheels_num, int doors_num, boolean is_automatic)
        {
            super(model, brand, color, wheels_num);
            this.doors_num = doors_num;
            this.is_automatic = is_automatic;
        }
        
        public String getInfo()
        {
            String msg = super.getInfo();
            msg += "\nNUMERO DE PORTAS: " + this.doors_num + "\n";
            msg += "TEM CAMBIO AUTOMATICO: " + this.is_automatic;
            return msg;
        }
        
        /**
         * Metodo para criar um clone do objeto
         * @return (Veicle) this.clone()
         */        
        @Override
        public Veicle getClone()
        {
            try {
                return (Veicle) this.clone();
            } catch (CloneNotSupportedException ex) {
                return (Veicle) this;
            }
        }
    }
    
    public class Motorcycle extends Veicle
    {
        public int engine_capacity;
        public boolean has_sidecar;
        
        public Motorcycle(String model, String brand, String color, int wheels_num, int engine_capacity, boolean has_sidecar)
        {
            
            super(model, brand, color, wheels_num);
            this.engine_capacity = engine_capacity;
            this.has_sidecar = has_sidecar;
        }
        
        public String getInfo()
        {
            String msg = super.getInfo();
            msg += "\nNUMERO DE CULINDRADAS: " + this.engine_capacity + "\n";
            msg += "TEM SIDECAR: " + this.has_sidecar;
            return msg;
        }
        
        /**
         * Metodo para criar um clone do objeto
         * @return (Veicle) this.clone()
         */
        @Override
        public Veicle getClone()
        {
            try {
                return (Veicle) this.clone();
            } catch (CloneNotSupportedException ex) {
                return (Veicle) this;
            }
        }
    }
}