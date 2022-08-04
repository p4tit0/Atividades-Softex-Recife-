const prompt = require('prompt-sync')();
const format = require('./format');

function Banco(acc_num, acc_type, agency, init_balance = 0)
{
    this.acc_num = acc_num;
    this.acc_type = acc_type;
    this.agency = agency;
    this.balance = init_balance;

    this.get_acc_num = () => this.acc_num;

    this.get_balance = () => this.balance;

    this.deposit = function (value) {
        this.balance += value;
        return this.balance;
    }

    this.withdraw = function (value) {
        if (this.balance > 0 && value <= this.balance)
            this.balance -= value;
        else
            console.log(format.msg(50, 'ERROR', 'SALDO INSUFICIENTE!', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
        return this.balance;
    }
}

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

function get_bool_input(msg, err_msg, true_value = 'S', false_value = 'N')
{
    var input;
    while (true)
    {
        input = prompt(msg).toUpperCase();
        if (input === true_value)
            return true;
        else if (input === false_value)
            return false;
        else
            console.log(format.msg(50, 'ERROR', err_msg, '-', {txt_bg_color: "RED", line_format: "BOLD"}));
    }
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1) ) + min;
}

console.log(format.title(50, 'SIMULADOR DE BANCO 24H', '-=-', {
    txt_bg_color: 'YELLOW',
    txt_format: 'ITALIC+BOLD',
    line_format: 'NONE'
}));
console.log('Bem vindo ao simulador de banco 24h!');
const has_acc = get_bool_input('Já tem uma conta no banco? (S/N)', 'INSIRA UMA OPÇÃO VÁLIDA (S/N)');

var acc_num = getRndInteger(100000000, 999999999);
var agency = getRndInteger(0, 9999);
var acc_type;
var balance = 0;
if (has_acc)
{
    acc_num = get_number_range_input('Insira o número da conta: ', 'INSIRA UM NÚMERO VÁLIDO', 999999999, 100000000);
    agency = get_number_range_input('Insira o número da agência: ', 'INSIRA UM NÚMERO VÁLIDO', 9999);
    console.log('Qual o tipo da sua conta?');
    console.log('   1 - conta-corrente;');
    console.log('   2 - conta de poupança');
    console.log('   3 - conta-salário');
    const op = get_number_range_input('Tipo da sua conta: ', 'INSIRA UM NÚMERO VÁLIDO', 3, 1);
    switch (op)
    {
        case 1:
            acc_type = 'conta-corrente';
            break;
        case 2:
            acc_type = 'conta de poupança';
            break;
        case 3:
            acc_type = 'conta-salário';
            break;
    }
}
else
{
    console.log('Qual o tipo da sua conta?');
    console.log('   1 - conta-corrente;');
    console.log('   2 - conta de poupança');
    console.log('   3 - conta-salário');
    const op = get_number_range_input('Tipo da sua conta: ', 'INSIRA UM NÚMERO VÁLIDO', 3, 1);
    switch (op)
    {
        case 1:
            acc_type = 'conta-corrente';
            break;
        case 2:
            acc_type = 'conta de poupança';
            break;
        case 3:
            acc_type = 'conta-salário';
            break;
    }
}

const bank = new Banco(acc_num, acc_type, agency, balance);

while (true)
{
    console.log('Escolha uma das seguntes operações:');
    console.log('   1 - Verificar saldo;')
    console.log('   2 - Deposito');
    console.log('   3 - Saque');
    console.log('   4 - Verificar número da conta');
    console.log('   0 - Sair\n');
    
    const op = get_number_range_input('Operação escolhida: ', 'ESCOLHA UMA OPERAÇÃO VÁLIDA!!!', 4);
    
    switch (op)
    {
        case 0:
            console.log('Até a próxima!');
            process.exit(0);
        case 1:
            console.log(`Seu saldo atual é R$${bank.get_balance()}`)
            break;
        case 2:
            var value = get_number_range_input('Valor depositado: ', 'INSIRA UM NÚMERO VÁLIDO!!!', Number.POSITIVE_INFINITY, 0);
            console.log(`Operação realizada com sucesso!\nO saldo atual da sua conta é R$${bank.deposit(value)}`);
            break;
        case 3:
            value = get_number_range_input('Valor a ser sacado: ', 'INSIRA UM NÚMERO VÁLIDO!!!', Number.POSITIVE_INFINITY, 0);
            console.log(`Operação realizada com sucesso!\nO saldo atual da sua conta é R$${bank.withdraw(value)}`);
            break;
        case 4:
            console.log(`Seu número da sua conta é ${bank.get_acc_num()}`)
            break;
    }
}

