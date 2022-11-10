-- ===================================
--      Criação do banco de dados     
-- ===================================

CREATE DATABASE sql_functions;
USE sql_functions;


-- ========================================
--      Criação do histórico de vendas    
-- ========================================

CREATE TABLE clients(
    id int UNSIGNED NOT NULL auto_increment,
    client_name VARCHAR(40) NOT NULL,
    client_value INT NOT NULL DEFAULT 0,
    registration_date DATETIME,

    PRIMARY KEY (id)
);

SELECT * FROM INVENTORY;

-- ========================================
--         Declaração do procedure        
-- ========================================

CREATE FUNCTION sum_today_clients
RETURNS TABLE 
AS
    RETURN (
        SELECT SUM(client_value) FROM clients 
        WHERE registration_date = datetime(GETDATE())
    );
END sum_today_clients;
