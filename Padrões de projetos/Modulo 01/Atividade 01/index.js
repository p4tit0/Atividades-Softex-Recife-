const prompt = require('prompt-sync')();
const format = require('./format');

/**
 * INTERFACE DO PRODUTO
 * 
 * como JavaScript não possui interfaces tive de fazer essa pequena gambiarra utilizando classes
 */
 class Computer {
    constructor() 
    {
        if (this.constructor === Computer)
            throw new Error('A classe "Computer" não pode ser instanciada');
        this.ram;
        this.hdd;
        this.cpu;
        this.type;
    }

    getRAM() {
        throw new Error('O método "getRAM()" deve ser implementado.');
    }
    getHDD() {
        throw new Error('O método "getHDD()" deve ser implementado.');
    }
    getCPU() {
        throw new Error('O método "getCPU()" deve ser implementado.');
    }
    getType() {
        throw new Error('O método "getType()" deve ser implementado.');
    }
    toString() {
        throw new Error('O método "toString()" deve ser implementado.');
    }
}

/**
 * CLASSE CONCRETA DOS SERVIDORES
 */
class Server extends Computer{
    constructor(ram, hdd, cpu) 
    {
        super();
        this.type = 'server';
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }
    getRAM() {
        return this.ram;
    }
    getHDD() {
        return this.hdd;
    }
    getCPU() {
        return this.cpu;
    }
    getType() {
        return this.type;
    }
    toString() {
        let value = 'RAM: ' + this.ram + 'Gb\n';
        value += 'HDD: ' + this.hdd + 'Gb\n';
        value += 'CPU: ' + this.cpu + 'GHz\n';
        value += 'Type: ' + this.type;
        return value;
    }
}

/**
 * CLASSE CONCRETA DOS PC'S
 */
class PC extends Computer{
    constructor(ram, hdd, cpu) 
    {
        super();
        this.type = 'pc';
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }
    getRAM() {
        return this.ram;
    }
    getHDD() {
        return this.hdd;
    }
    getCPU() {
        return this.cpu;
    }
    getType() {
        return this.type;
    }
    toString() {
        let value = 'RAM: ' + this.ram + 'Gb\n';
        value += 'HDD: ' + this.hdd + 'Gb\n';
        value += 'CPU: ' + this.cpu + 'GHz\n';
        value += 'Type: ' + this.type;
        return value;
    }
}


/**
 * CLASSE ABSTRATA "Creator"
 */
class ComputerFactory
{
    constructor()
    {
        if (this.constructor === ComputerFactory)
            throw new Error('A classe "ComputerFactory" não pode ser instanciada');
    }

    setType(value)
    {
        if (!(value === 'server' || value === 'pc'))
            throw new Error('Tipo inváido');    
        this.type = value;            
    }

    setRAM(value)
    {
        if (value < 0)
            throw new Error('Valór inválido');
        this.ram = value;
    }

    setHDD(value)
    {
        if (value < 0)
            throw new Error('Valór inválido');
        this.hdd = value;
    }

    setCPU(value)
    {
        if (value < 0)
            throw new Error('Valór inválido');
        this.cpu = value;
    }
}

/**
 * CLASSE CONCRETA "ConcreteCreator"
 */
class ConcreteComputerFactory extends ComputerFactory
{
    constructor()
    {
        super();
    }

    createComputer()
    {
        switch (this.type)
        {
            case 'server':
                return new Server(this.ram, this.hdd, this.cpu);
            case 'pc':
                return new PC(this.ram, this.hdd, this.cpu);
            default:
                throw new Error('Tipo de computador inválido');
                break;
        }
    }
}

/**
 * CÓDIGO DO CLIENTE
 */

 function get_number_range_input(msg, err_msg, max, min = 0)
 {
     var input;
     while (true)
     {
         input = Number(prompt(msg));
         if (!Number.isNaN(input) && input <= max && input >= min)
             break;
         console.log(format.msg(50, 'ERROR', err_msg, '-', {txt_bg_color: "RED", line_format: "BOLD"}));
     }
     return input;
 }

 function get_number_input(msg, err_msg)
 {
    var input;
    while (true)
    {
        input = Number(prompt(msg));
        if (!Number.isNaN(input))
            break;
        console.log(format.msg(50, 'ERROR', err_msg, '-', {txt_bg_color: "RED", line_format: "BOLD"}));
    }
    return input;
 }


const comp_creator = new ConcreteComputerFactory();
var coputer_list = [];

console.log(format.title(50, 'FABRICA DE COMPUTADORES', '-=-', {
    txt_bg_color: 'BLACK',
    txt_color: 'WHITE',
    txt_format: 'ITALIC+BOLD',
    line_format: 'INVERT'
}));

console.log('Bem vindo a fabrica de computadores!');
while (true)
{
    console.log('O que você deseja fazer?');
    console.log('   1 - Criar um novo computador');
    console.log('   2 - Ver computadores criados');
    console.log('   0 - Sair\n');

    const op = get_number_range_input('Opção escolhida: ', 'ESCOLHA UMA OPÇÃO VÁLIDA!!!', 2);
    switch (op)
    {
        case 0:
            console.log('Até a próxima!');
            process.exit(0);
        case 1:
            let type = prompt('Qual o tipo do computador (server/pc)? ').trim().toLowerCase();
            comp_creator.setType(type);
            let ram = get_number_input('Insira o valor da RAM: (Gb)', 'INSIRA UM NÚMERO!');
            comp_creator.setRAM(ram);
            let hdd = get_number_input('Insira o tamanho do HD: (Gb)', 'INSIRA UM NÚMERO!');
            comp_creator.setHDD(hdd);
            let cpu = get_number_input('Insira o clock da CPU: (GHz)', 'INSIRA UM NÚMERO!');
            comp_creator.setCPU(cpu);
            coputer_list.push(comp_creator.createComputer());
            break;
        case 2:
            for (let i = 0; i < coputer_list.length; i++)
            {
                console.log(format.msg(50, i+1, coputer_list[i].toString(), '-'));
            }
            break;
    }
    console.log();
}