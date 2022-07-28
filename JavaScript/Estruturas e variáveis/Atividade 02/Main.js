// Antes de executar o código digite "npm install prompt-sync" no terminal e pressione enter.
const prompt = require('prompt-sync')();

var n1;
while (true)
{
    n1 = Number(prompt('Primeiro valor: '));
    if (!Number.isNaN(n1))
        break;
    console.log('ERROR: INSIRA UM NÚMERO VÁLIDO!');
}
var n2;
while (true)
{
    n2 = Number(prompt('Segundo valor: '));
    if (!Number.isNaN(n2))
        break;
    console.log('ERROR: INSIRA UM NÚMERO VÁLIDO!');
}
console.log();
console.log('Agora escolha uma das seguntess operações: ');
console.log(' ~ Soma(+)');
console.log(' ~ Subtração(-)');
console.log(' ~ Multiplicação(*)');
console.log(' ~ Divisão(/)');
console.log(' ~ Potenciação(**)');
console.log(' ~ Módulo(%)');
console.log();
var op;
while (true)
{
    op = prompt('Operação escolhida: ');
    

    switch (op)
    {
        case '+':
            console.log();
            console.log('O resultado da soma é ' + (n1 + n2));
            break;
        case '-':
            console.log();
            console.log('O resultado da subtração é ' + (n1 - n2));
            break;
        case '*':
            console.log();
            console.log('O resultado da multiplicação é ' + (n1 * n2));
            break;
        case '/':
            console.log();
            console.log('O resultado da divisão é ' + Number.parseInt(n1 / n2));
            var mod = n1 % n2;
            if (mod != 0)
                console.log('E o resto é ' + mod);
            break;
        case '**':
            console.log();
            console.log('O resultado da potenciação é ' + (n1 ** n2));
            break;
        case '%':
            console.log();
            console.log('O resultado do módulo é ' + (n1 % n2));
            break;
        default:
            console.log('ERROR: ESCOLHA UMA OPERAÇÂO VÁLIDA!');
            continue;
    }
    break;
}
