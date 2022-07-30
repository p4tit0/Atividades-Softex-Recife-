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


const n_notas = 3;
console.log(format.title(50, 'AVALIADOR DE SITUAÇÃO', '-=-', {txt_bg_color: 'CYAN', txt_format: 'ITALIC+BOLD', line_format: 'NONE'}));
console.log('Insira as ' + n_notas + ' notas do aluno e o sistema dirá a situação do mesmo')

var sum = 0;
for (let i = 0; i < n_notas; i++) 
    sum += get_number_range_input((i + 1) + 'ª nota: ', 'Insira uma nota válida!!! (0-10)', 10);

const media = sum/3;
const situacao = (media >= 7) ? 'APROVADO' : 'REPROVADO'

console.log(format.title(50, 'O aluno foi ' + situacao, '-', {txt_bg_color: (media >= 7) ? 'GREEN' : 'RED', txt_format: 'BOLD', line_format: 'NONE'}));
