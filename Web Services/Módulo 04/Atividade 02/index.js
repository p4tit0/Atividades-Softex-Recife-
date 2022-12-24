const express = require('express');
const bodyParser = require('body-parser');
const routes = require("./routes/index.js");

const app =  express();
const port = 8080;

app.use(bodyParser.json());

routes(app)

app.listen(port, () => {
    console.log(`Server listening on port ${port}`)
})