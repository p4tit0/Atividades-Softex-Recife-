const express = require('express');
const fs = require('fs');

const app = express();
const conn = JSON.parse(fs.readFileSync('src/conn.json'));
app.listen(conn.port);

for (let route of Object.entries(conn.routes))
{
    for (let subroute of route[1])
    {
        app.get(`/${route[0]}/${subroute}`, (request, response) => {
            return response.send(`Hello! This is the ${route[0]} route "${subroute}".`);
        });
    }
}