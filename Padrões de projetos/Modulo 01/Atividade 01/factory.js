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

class ComputerFactory
{
    constructor()
    {
        if (this.constructor === ComputerFactory)
            throw new Error('A classe "ComputerFactory" não pode ser instanciada');
    }
}

class ConcreteComputerFactory extends ComputerFactory
{
    constructor(ram, hdd, cpu, type)
    {
        super();
        switch(type)
        {
            case 'server':
                break;
            case 'pc':
                break;
            default:
                break;
        }
    }
}
