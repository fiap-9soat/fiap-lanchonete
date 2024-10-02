CREATE DATABASE IF NOT EXISTS lanchonete;

USE lanchonete;

CREATE TABLE Clientes (
    codigo_cliente INT NOT NULL AUTO_INCREMENT,
    cpf CHAR(11) NOT NULL,
    nome VARCHAR(14) NOT NULL,
    sobrenome VARCHAR(16) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY (codigo_cliente)
);
CREATE UNIQUE INDEX idx1 ON Clientes(cpf);

CREATE TABLE Alimentos (
    codigo_tipo_alimento SMALLINT NOT NULL,
    codigo_alimento SMALLINT NOT NULL,
    nome_tipo_alimento VARCHAR(30) NOT NULL,
    nome_alimento VARCHAR(30) NOT NULL,
    nome_funci_alter VARCHAR(20) NOT NULL,
    ts_alter TIMESTAMP NOT NULL,
    PRIMARY KEY (codigo_tipo_alimento, codigo_alimento)
);
CREATE UNIQUE INDEX idx2 ON Alimentos(nome_tipo_alimento, nome_alimento);

CREATE TABLE Pedidos (
    codigo_pedido INT NOT NULL,
    codigo_cliente INT,
    ts_pedido TIMESTAMP NOT NULL,
    codigo_tipo_alimento SMALLINT,
    codigo_alimento SMALLINT,
    estado_pedido SMALLINT NOT NULL,
    PRIMARY KEY (codigo_pedido, codigo_cliente, ts_pedido),
    FOREIGN KEY (codigo_cliente) 
        REFERENCES Clientes(codigo_cliente) ON UPDATE CASCADE,
    FOREIGN KEY (codigo_tipo_alimento, codigo_alimento) 
        REFERENCES Alimentos(codigo_tipo_alimento, codigo_alimento) ON UPDATE CASCADE
);

CREATE TABLE Hist_Pedidos (
    codigo_pedido INT NOT NULL,
    codigo_cliente INT,
    ts_pedido TIMESTAMP NOT NULL,
    ts_alter TIMESTAMP NOT NULL,
    codigo_tipo_alimento SMALLINT,
    codigo_alimento SMALLINT,
    estado_pedido SMALLINT NOT NULL,
    PRIMARY KEY (codigo_pedido, codigo_cliente, ts_pedido, ts_alter),
    FOREIGN KEY (codigo_cliente) 
        REFERENCES Clientes(codigo_cliente) ON UPDATE CASCADE,
    FOREIGN KEY (codigo_tipo_alimento, codigo_alimento) 
        REFERENCES Alimentos(codigo_tipo_alimento, codigo_alimento) ON UPDATE CASCADE
);

INSERT INTO Clientes (cpf, nome, sobrenome, email) VALUES ("12345678910", "João", "da Silva", "joaodasilva@abc.com.br");


INSERT INTO Alimentos (codigo_tipo_alimento, codigo_alimento, nome_tipo_alimento, nome_alimento, nome_funci_alter, ts_alter) 
VALUES (1, 1, "Lanche", "X-burger", "João", CURRENT_TIMESTAMP());


INSERT INTO Pedidos (codigo_pedido, codigo_cliente, ts_pedido, codigo_tipo_alimento, codigo_alimento, estado_pedido) 
VALUES (1, 1, CURRENT_TIMESTAMP(), 1, 1, 1);

INSERT INTO Hist_pedidos (codigo_pedido, codigo_cliente, ts_pedido, ts_alter, codigo_tipo_alimento, codigo_alimento, estado_pedido) 
VALUES (1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 1, 1);
