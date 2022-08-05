const prompt = require('prompt-sync')();
const format = require('./format');

function Book(name, author, release_year)
{
    this.name = name;
    this.author = author;
    this.release_year = release_year;
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

function print_book_proprieties(book)
{
    for (const prop in book)
        console.log(`${prop.toUpperCase()}: ${book[prop]}`);
}

function print_books(arr)
{
    var pos = 0;
    for (book of arr)
    {
        console.log(`${pos}: ${book.name}`);
        pos += 1;
    }
    
}

var arr = [];

console.log(format.title(50, 'SIMULAOR DE BIBLIOTECA', '-=-', {
    txt_bg_color: 'BLACK',
    txt_color: 'WHITE',
    txt_format: 'ITALIC+BOLD+INVERT',
    line_format: 'INVERT'
}));

console.log('Bem vindo ao simulador de biblioteca!');

while (true)
{
    console.log('O que você deseja fazer?');
    console.log('   1 - Adicionar um livro');
    console.log('   2 - Remover um livro');
    console.log('   3 - Navegar pela biblioteca');
    console.log('   0 - Sair\n');

    const op = get_number_range_input('Opção escolhida: ', 'ESCOLHA UMA OPÇÃO VÁLIDA!!!', 3);
    console.log()

    switch (op)
    {
        case 0:
            console.log('Até a próxima!');
            process.exit(0);
        case 1:
            const book_name = prompt('Insira o nome do livro: ');
            const book_author = prompt('Insira o nome do Autor: ');
            const release_year = get_number_range_input('Insira o ano de lançamento: ', 'INSIRA UM ANO VÁLIDO', new Date().getFullYear());
            arr.push(new Book(book_name, book_author, release_year));
            break;
        case 2:
            if (arr.length <= 3)
            {
                console.log(format.msg(50, 'ERROR', 'O ACERVO DEVE TER PELO MENOS 3 LIVROS', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
                continue;
            }
            array.splice(index, get_number_range_input('Insira a posição do livro: ', 'INSIRA UM NÚMERO VÁLIDO', arr.length-1));
            break;
        case 3:
            if (arr.length < 3)
            {
                console.log(format.msg(50, 'ERROR', 'O ACERVO DEVE TER PELO MENOS 3 LIVROS', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
                continue;
            }
            
            nestedloop: while (true)
            {
                console.log('Como você deseja navegar?');
                console.log('   1 - Ver todo o acervo');
                console.log('   2 - Ver informações de um livro');
                console.log('   0 - voltar\n');

                const op1 = get_number_range_input('Opção escolhida: ', 'ESCOLHA UMA OPÇÃO VÁLIDA!!!', 2);
                console.log()
                switch (op1)
                {
                    case 0:
                        break nestedloop;
                    case 1:
                        print_books(arr);
                        break;
                    case 2:
                        print_book_proprieties(arr[get_number_range_input('Insira a posição do livro: ', 'INSIRA UM NÚMERO VÁLIDO', arr.length-1)])
                        break;
                }
                console.log();
            }
            break;
    }
    console.log();
}