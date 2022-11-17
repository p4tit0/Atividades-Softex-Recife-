/**
 * Servidor básico em Node.js
 * 
 * Para executar o servidor utilize o comando "node server.js"
 * Para acessar o site utilize a URL "localhost:8080"
 * 
 * @Author Vinícius Santos Lima
 */

const {createServer} = require("http");

const PORT = process.env.PORT || 8080;
const SERVER = createServer();

SERVER.on("request", (request, response) => {
    response.end("Hello World!");
});

SERVER.listen(PORT, ()=>{
    console.log(`Servidor iniciado na porta ${PORT}`);
});