const express = require('express'); 
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 8080;

let books = [];

app.use(cors());

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


app.get('/books/read/:id', function(req, res) {
    let id = req.params.id;
    if (id > 0 && id <= books.length)
        res.status(200).send(books[id - 1]);
    else
        res.status(404).send('Livro não encortrado!');
});

app.get('/books/read_all', function(req, res) {
     res.status(200).send(books);
});

app.post('/books/add', function(req, res) {
    const book = req.body;
    let pos = books.length + 1

    books.push(book);
    book.pos = pos;

    console.log(book);

    res.status(201).send(book);
});

app.put('/books/edit/:id', function(req, res) {
    let id = req.params.id;
    if (id > 0 && id <= books.length) {
        
        let book = req.body;
        books[id-1] = book;

        res.status(200).send(books[id - 1]);
    
    } else {
        res.status(404).send('Livro não encortrado!');
    }
});

app.delete('/books/remove/:id', function(req, res) {
    let id = req.params.id;
    if (id > 0 && id <= books.length) {
        books.splice(id - 1, 1);

        res.status(200).send('O livro foi removido com sucesso!');
    
    } else {
        res.status(404).send('Livro não encortrado!');
    }
});

app.all('*', function (req, res) {
    res.status(404).send('Página não encontrada!');
});

app.listen(port, function() {
    console.log(`Servidor escutando na porta ${port}!`);
});
