-- ===================================
--      Criação do banco de dados     
-- ===================================

CREATE DATABASE sql_procedures;
USE sql_procedures;

-- ========================================
--            Criação do estoque           
-- ========================================

CREATE TABLE inventory(
    id int UNSIGNED NOT NULL auto_increment,
    item_name varchar(50) NOT NULL,
    item_description varchar(300) NOT NULL,
    amount int UNSIGNED NOT NULL,
    price decimal(10,2) UNSIGNED NOT NULL,

    PRIMARY KEY (id)
);

SELECT * FROM inventory;

-- ========================================
--      Criação do histórico de vendas    
-- ========================================

CREATE TABLE sales_history(
    id int UNSIGNED NOT NULL auto_increment,
    item_id int UNSIGNED NOT NULL,
    num_of_items int NOT NULL DEFAULT 0,
    profit decimal(10,2) UNSIGNED NOT NULL,
    transaction_date datetime,

    PRIMARY KEY (id)
);

SELECT * FROM sales_history;

-- ========================================
--         Declaração do procedure        
-- ========================================

CREATE PROCEDURE get_today_sales 
AS
    SELECT 
    	item_id as id, 
        item_name as name, 
        SUM(num_of_items) as quant
    FROM sales_history 
    	INNER JOIN inventory ON sales_history.item_id = inventory.id
    WHERE transaction_date = datetime(GETDATE())
    GROUP BY id;
    

