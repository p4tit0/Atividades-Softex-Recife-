-- 1. inclua a coluna DATA_NASCIMENTO na tabela ALUNO do tipo string e de tamanho 10 caracteres
ALTER TABLE ALUNO ADD DATA_NASCIMENTO varchar(10);

-- 2. altere a coluna TELEFONE para CONTATO e seu tipo de dado para string
ALTER TABLE ALUNO RENAME COLUMN TELEFONE TO CONTATO;
ALTER TABLE ALUNO MODIFY COLUMN TELEFONE varchar(255);

-- 3. inclua o campo ISBN na tabela LIVRO, com tamanho de 13 caracteres do tipo inteiro
ALTER TABLE LIVRO ADD ISBN INT(13);

-- 4. e remova o campo ISBN da tabela LIVRO
ALTER TABLE LIVRO DROP COLUMN ISBN 
