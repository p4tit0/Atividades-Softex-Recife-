const express = require('express');
const app = express();

app.get('/', function(req, res) {
    res.send("Recebi um HTTP GET");
})

app.post('/', function(req, res) {
    res.send("Recebi um HTTP POST");
})

app.put('/', function(req, res) {
    res.send("Recebi um HTTP PUT");
})

app.delete('/', function(req, res) {
    res.send("Recebi um HTTP DELETE");
})

app.listen(8080, function(){
    console.log("Servidor escutando na porta 8080");
})