const express = require('express');
const mysql = require('mysql');

const router = express.Router();
const con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: 'games_db'
  });

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});

router
    .get("/games", (req, res) => {
        console.log("Busca")

        con.query('SELECT * FROM games ORDER BY id;', (err, result) => {
            if (err) throw err;
            res.status(200).send(result)
        })
    })


    .get("/games/:id", (req, res) => {
        console.log("Busca por id")

        con.query(`SELECT * FROM games WHERE id = ${req.params.id};`, (err, result) => {
            if (err) throw err;
            res.status(200).send(result)
        })
    })


    .post("/games", (req, res) => {
        console.log("Cadastro");

        con.query(`INSERT INTO games (id, name, release_date) VALUES (NULL, "${req.body.name}" , ${req.body.release_date} );`, (err, result) => {
            if (err) throw err;
            res.status(201).send(result)
        })
    })

    
    .put("/games/:id", (req, res) => {
        console.log("Update")

        con.query(`UPDATE games SET name = "${req.body.name}" , release_date = "${req.body.release_date}" WHERE id_cliente = ${req.params.id}`, (err, result) => {
            if (err) throw err;
            res.status(200).send(result)
        })
    })

    
    .delete("/games/:id", (req, res) => {
        console.log("Delete")

        con.query(`DELETE FROM games WHERE id = ${req.params.id};`, (err, result) => {
            if (err) throw err;
            res.status(200).send(result)
        })
    })

module.exports = router;