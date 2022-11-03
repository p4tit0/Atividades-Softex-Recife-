/**
 * Resolução da atividade do desenvolvimento 08 sobre banco de dados.
 * O intuito deste código é praticar a utilização de triggers.
 *
 * @Autor Vinícius Santos Lima
 */

-- ===================================
--      Criação do banco de dados     
-- ===================================

CREATE DATABASE sql_trigger;
USE sql_trigger;

-- ===================================
--          Criação da tabela     
-- ===================================

CREATE TABLE INVENTORY(
    id int UNSIGNED NOT NULL auto_increment,
    item_name varchar(50) NOT NULL,
    item_description varchar(300) NOT NULL,
    amount int UNSIGNED NOT NULL,
    price decimal(10,2) UNSIGNED NOT NULL,

    PRIMARY KEY (id)
);

SELECT * FROM INVENTORY;

-- ===================================
--       Utilização de triggers
-- ===================================

CREATE TRIGGER TGR_STOCK_AI
ON INVENTORY
BEFORE INSERT OR UPDATE 
AS
BEGIN
    DECLARE
    @VALUE          DECIMAL(10,2),
    @NEW_AMOUNT     INT UNSIGNED,
    @PREV_AMOUNT    INT UNSIGNED
    
    
    IF INSERTING
        SELECT @NEW_AMOUNT = amount, @VALUE = price FROM INSERTED

        PRINT 'Você gastou R$' + CAST((@VALUE * @NEW_AMOUNT) AS varchar(25))
    END IF;
    IF UPDATING
        SELECT @PREV_AMOUNT = amount FROM DELETED
        SELECT @NEW_AMOUNT = amount, @VALUE = price FROM INSERTED

        PRINT 'Seu lucro foi de ' +  CAST((@VALUE * (@NEW_AMOUNT - @PREV_AMOUNT)) AS varchar(25)) + 'reais'
    END IF;
END
GO
