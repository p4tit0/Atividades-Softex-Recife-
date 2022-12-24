const express = require('express');
const games = require("./gamesRoutes.js");

const routes = function(app) {
    app.route('/').get((req, res) => {
        res.status(200).send({title: "Example"})
    })

    app.use(
        express.json(),
        games
    )
}

module.exports = routes;