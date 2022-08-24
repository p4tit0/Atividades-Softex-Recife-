const Sequelize = require('sequelize');
const sequelize = new Sequelize ('db-vini', 'root', '', {dialect: 'mysql', host: 'localhost'});

const format = require('./format');

sequelize.authenticate().then(() => {
    console.log(format.msg(50, 'CONEXÃO ESTABELECIDA', 'Conexão estabelecida com sucesso.', '-', {txt_bg_color: "GREEN", line_format: "BOLD"}));
 }).catch((error) => {
    console.log(format.msg(50, 'ERROR', 'Erro ao conectar ao banco de dados!', '-', {txt_bg_color: "RED", line_format: "BOLD"}));
    console.error('Detalhes:', error);
 });

module.exports = sequelize;
