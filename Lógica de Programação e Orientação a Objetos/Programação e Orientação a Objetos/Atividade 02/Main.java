class Empresa
{
    class Funcionario
    {
        String nome;
        double salario;

        public Funcionario(String nome, double salario)
        {
            this.nome = nome;
            this.salario = salario;
        }
    }

    class FuncNode
    {
        Funcionario funcionario;
        FuncNode prox_func;

        public FuncNode(Funcionario funcionario)
        {
            this.funcionario = funcionario;
            this.prox_func = null;
        }

        public void add_node(Funcionario funcionario)
        {
            FuncNode current_node = this;
            while (current_node.prox_func != null) 
            {
                current_node = current_node.prox_func;
            }
            current_node.prox_func = new FuncNode(funcionario);
        }

        public Funcionario[] get_func_list(int len)
        {
            Funcionario[] func_list = new Funcionario[len];
            FuncNode current_node = this;

            int i = 0;
            while (current_node != null) 
            {
                func_list[i] = current_node.funcionario;
                current_node = current_node.prox_func;
                i++;
            }

            return func_list;
        }
    }
    
    static int num_func;
    static String nome_da_empresa;
    static FuncNode lista_de_func;
    
    public Empresa(String nome)
    {
        this.num_func = 0;
        this.nome_da_empresa = nome;
        this.lista_de_func = null;
    }

    public void add_func(String nome, double salario)
    {
        if (salario < 0)
            throw new IllegalArgumentException("Salário inválido!");

        if (lista_de_func == null)
            lista_de_func = new FuncNode(new Funcionario(nome, salario));
        else
            lista_de_func.add_node(new Funcionario(nome, salario));
        increment_func_num();
    }

    public static void increment_func_num()
    {
        num_func++;
    }

    public Funcionario[] pegar_lista()
    {
        return lista_de_func.get_func_list(num_func);
    }
    
    public String pegar_lista_string()
    {
        Funcionario[] list = this.pegar_lista();
        String list_str = "";
        
        for (int i = 0; i < list.length; i++)
        {
            list_str += String.valueOf(i+1) + ": " + list[i].nome + " (R$"+ String.valueOf(list[i].salario) + ")\n";
        }
            

        return list_str;
    }
    
}

class Main 
{
    public static void main(String[] args) 
    {
        /**
        * Como os atributos "num_func", "nome_da_empresa", "lista_de_func" foram definidos como estaticos
        * independente de quantos objetos do tipo "Empresa" eu criar seus atributos serão compartilhados,
        * ou seja, todos eles são essencialmente a mesma empresa.
        */
        
        Empresa emp1 = new Empresa("EA");
        Empresa emp2 = new Empresa("Applesoft");
        Empresa emp3 = new Empresa("Softex");

        System.out.println(emp1.nome_da_empresa);
        System.out.println(emp2.nome_da_empresa);
        System.out.println(emp3.nome_da_empresa);

        emp1.add_func("João", 0.5);   
        emp2.add_func("Pedro", 975.3);
        emp2.add_func("Carla", 1354.7);

        System.out.print("\n");
        System.out.println("A Empresa 01 tem " + String.valueOf(emp1.num_func) + " funcionarios");
        System.out.println("Lista de funcionários da Empresa 01:");
        System.out.print(emp1.pegar_lista_string());

        System.out.print("\n");
        System.out.println("A Empresa 02 tem " + String.valueOf(emp2.num_func) + " funcionarios");
        System.out.println("Lista de funcionários da Empresa 02:");
        System.out.print(emp2.pegar_lista_string());

        System.out.print("\n");
        System.out.println("A Empresa 03 tem " + String.valueOf(emp3.num_func) + " funcionarios");
        System.out.println("Lista de funcionários da Empresa 03:");
        System.out.print(emp3.pegar_lista_string());
    }
}