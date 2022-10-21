CREATE DATABASE sql_joins;
USE sql_joins;

CREATE TABLE TABLE_A(
    id int unsigned not null auto_increment,
    info char not null,
    
    PRIMARY KEY (id)
);

INSERT INTO TABLE_A (id, info) VALUES (1, 'A');
INSERT INTO TABLE_A (id, info) VALUES (2, 'B');
INSERT INTO TABLE_A (id, info) VALUES (3, 'C');
INSERT INTO TABLE_A (id, info) VALUES (4, 'D');
INSERT INTO TABLE_A (id, info) VALUES (5, 'E');

SELECT * FROM TABLE_A;

CREATE TABLE TABLE_B(
    id int unsigned not null auto_increment,
    info char not null,
    
    PRIMARY KEY (id)
);

INSERT INTO TABLE_B (id, info) VALUES (1, 'D');
INSERT INTO TABLE_B (id, info) VALUES (2, 'E');
INSERT INTO TABLE_B (id, info) VALUES (3, 'F');
INSERT INTO TABLE_B (id, info) VALUES (4, 'G');
INSERT INTO TABLE_B (id, info) VALUES (5, 'H');

SELECT * FROM TABLE_B;

-- Inner JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
INNER JOIN TABLE_B 
	ON TABLE_A.info=TABLE_B.info;

-- Left JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
LEFT JOIN TABLE_B 
	ON TABLE_A.info=TABLE_B.info;

-- Right JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
RIGHT JOIN TABLE_B 
	ON TABLE_A.info=TABLE_B.info;

-- Outer JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
FULL OUTER JOIN TABLE_B
	ON TABLE_A.info = TABLE_A.info;

-- Left Excluding JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
LEFT JOIN TABLE_B 
	ON TABLE_A.info=TABLE_B.info WHERE TABLE_B.info IS NULL;

-- Right Excluding JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
RIGHT JOIN TABLE_B 
	ON TABLE_A.info=TABLE_B.info WHERE ABLE_A.info IS NULL;

-- Outer Excluding JOIN
SELECT TABLE_A.info, TABLE_B.info
FROM TABLE_A
FULL OUTER JOIN TABLE_B
	ON TABLE_A.info = TABLE_A.info WHERE TABLE_A.info IS NULL OR TABLE_B.info IS NULL;
