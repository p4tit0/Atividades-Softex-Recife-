const Sequelize = require ('sequelize');
const database = require('./db');

const User = database.define('users', {
    username: {
        type: Sequelize.STRING,
        allowNull: false
    },
    password: {
        type: Sequelize.STRING,
        allowNull: false
    },
    gender: {
        type: Sequelize.CHAR,
        allowNull: false
    }
});

module.exports = User;