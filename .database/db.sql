CREATE DATABASE IF NOT EXISTS lanchonete;

USE lanchonete;

DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
    emp_no      INT             NOT NULL,  -- UNSIGNED AUTO_INCREMENT??
    birth_date  DATE            NOT NULL,
    first_name  VARCHAR(14)     NOT NULL,
    last_name   VARCHAR(16)     NOT NULL,
    gender      ENUM ('M','F')  NOT NULL,  -- Enumeration of either 'M' or 'F'  
    hire_date   DATE            NOT NULL,
    PRIMARY KEY (emp_no)                   -- Index built automatically on primary-key column
                                           -- INDEX (first_name)
                                           -- INDEX (last_name)
);

INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES (1, "2018-07-21", 'João', 'Fulano', 'M', '2018-07-21"');