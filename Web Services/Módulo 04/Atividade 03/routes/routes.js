const { faker } = require('@faker-js/faker');

let appRouter = function (app) {

    app.get("/", function(req, res) {
        res.status(200).send("Bem vindo ao [insira nome do webservice]!")
    });

    app.get("/user", function(req, res) {
        let user_info = {
            firstName: faker.name.firstName(),
            lastName: faker.name.lastName(),
            username: faker.internet.userName(),
            email: faker.internet.email()
        };
       res.status(200).send(user_info)
    });

    app.get("/user/:num", function(req, res) {
        let users = [];
        let num = req.params.num;
        if (isFinite(num) && num > 0) {
            for (let i = 0; i <= num-1; i++) {
                users.push({
                    firstName: faker.name.firstName(),
                    lastName: faker.name.lastName(),
                    username: faker.internet.userName(),
                    email: faker.internet.email()
                });
            }

            res.status(200).send(users)
        } else {
            res.status(400).send({ message: 'Número de usuários inválido' })
        }
    });

    app.post("/signup", function(req, res) {
        const user = req.body.user
        if (user.firstName != undefined && user.lastName != undefined && 
            user.username != undefined && user.email != undefined) {
                res.status(201).send({ message: 'Usuário savo com sucesso!' })
        } else {
            res.status(400).send({ message: 'Faltam algumas informações do usuário!' })
        }
        
    });
}

module.exports = appRouter