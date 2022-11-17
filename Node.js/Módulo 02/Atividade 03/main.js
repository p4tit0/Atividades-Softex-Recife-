const express = require('express');
const app = express();

app.get('/batata', function(req, res) {
    let msg = "BATATA! "
    for (let i = 0; i < 16; i++) {msg += msg;}
    res.send(msg);
})

app.get('/user:id', function(req, res) {
    const ID = req.params.id
    res.send(`ID recebido: ${ID}`);
})

app.get('/book', function(req, res) {
    const page = req.query.page
    const msg = "NEVER GONNA GIVE YOU UP!";
    res.send(`Página: ${page}<br><br>${msg[page]}`);
})

app.listen(8080, function(){
    console.log("Servidor escutando na porta 8080");
})