const prompt = require('prompt-sync')();
const format = require('./format');

const database = require ('./db');
const User = require('./model');

async function run() {

        await database.sync().then(() => {
            console.log(format.msg(50, 'TABELA SINCRONIZADA', 'Tabela sicronizada com sucesso.', '-', {txt_bg_color: "GREEN", line_format: "BOLD"}));
        
            User.create({
                username: "Novo usuário",
                password: "123456",
                gender: "O",
            }).then(res => {
                console.log(res)
            }).catch((error) => {
                console.log(format.msg(50, 'ERROR', 'Erro ao criar um novo registo!', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
                console.error('Detalhes: ', error);
            });
        
        }).catch((error) => {
            console.log(format.msg(50, 'ERROR', 'Erro ao sincronizar tabela!', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
        });

}

run();
