const express = require('express');
const app = express();

app.get('/', function(req, res) {
    res.send("Get");
})

app.post('/', function(req, res) {
    res.send('Post');
})

app.listen(8080, function(){
    console.log("Servidor escutando na porta 8080");
})