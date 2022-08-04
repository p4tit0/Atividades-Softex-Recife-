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
            return response.send(`Hello!<br>This is the ${route[0]} route "${subroute}".<br><img src="https://catiororeflexivo.com/wp-content/uploads/2020/07/funny-expressive-dog-corgi-genthecorgi-1-22-5f0ea744e82d4__700.jpg"  width="300">`);
        });
    }
}