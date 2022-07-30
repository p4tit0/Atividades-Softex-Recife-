// Antes de executar o código digite "npm install prompt-sync" no terminal e pressione enter.
const prompt = require('prompt-sync')();
const format = require('./format');


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


const n_notas = 2;
console.log(format.title(50, 'CALCULADORA DE NOTAS', '-=-', {txt_bg_color: 'MAGENTA', txt_format: 'ITALIC+BOLD', line_format: 'NONE'}));
console.log('Insira as suas ' + n_notas + ' primeiras notas e o sistema dirá quanto você precisa obter na ' + (n_notas+1) + 'ª prova')

var sum = 0;
for (let i = 0; i < n_notas; i++) 
    sum += get_number_range_input((i + 1) + 'ª nota: ', 'Insira uma nota válida!!! (0-10)', 10);

const nota_rest = (3 * 7) - sum;
const color = (nota_rest > 10) ? 'RED' : ((nota_rest >= 7) ? 'YELLOW' : 'GREEN');
const ret_msg = (nota_rest > 10) ? 'Você já está reprovado!!!' : 'Você precisa obter ' + nota_rest + ' na próxima prova!' 

console.log(format.title(50, ret_msg, '-', {txt_bg_color: color, txt_format: 'BOLD', line_format: 'NONE'}));
