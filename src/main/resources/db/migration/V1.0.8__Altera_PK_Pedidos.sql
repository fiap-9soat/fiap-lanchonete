DROP TABLE Pedidos;
DROP TABLE Hist_Pedidos;
CREATE TABLE Pedidos (
    codigo_pedido INT NOT NULL,
    codigo_cliente INT,
    ts_ultimo_pedido TIMESTAMP NOT NULL,
    estado_pedido SMALLINT NOT NULL,
    PRIMARY KEY (codigo_pedido, codigo_cliente),
    FOREIGN KEY (codigo_cliente) REFERENCES Clientes(codigo_cliente) ON UPDATE CASCADE
);
CREATE TABLE Hist_Pedidos (
    codigo_pedido INT NOT NULL,
    codigo_cliente INT,
    ts_ultimo_pedido TIMESTAMP NOT NULL,
    ts_alter TIMESTAMP NOT NULL,
    estado_pedido SMALLINT NOT NULL,
    PRIMARY KEY (
        codigo_pedido,
        codigo_cliente,
        ts_alter
    ),
    FOREIGN KEY (codigo_cliente) REFERENCES Clientes(codigo_cliente) ON UPDATE CASCADE
);
create table Pedidos_Alimentos (
    codigo_pedido INT,
    codigo_tipo_alimento SMALLINT,
    codigo_alimento SMALLINT,
    qtdade_alimento SMALLINT NOT NULL,
    PRIMARY KEY (
        codigo_pedido,
        codigo_tipo_alimento,
        codigo_alimento
    ),
    FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido) ON UPDATE CASCADE,
    FOREIGN KEY (codigo_tipo_alimento, codigo_alimento) REFERENCES Alimentos(codigo_tipo_alimento, codigo_alimento) ON UPDATE CASCADE
);
create table Hist_Pedidos_Alimentos (
    codigo_pedido INT,
    codigo_tipo_alimento SMALLINT,
    codigo_alimento SMALLINT,
    qtdade_alimento SMALLINT NOT NULL,
    ts_alter TIMESTAMP NOT NULL,
    tipo_alter CHAR(1) NOT NULL,
    PRIMARY KEY (
        codigo_pedido,
        codigo_tipo_alimento,
        codigo_alimento
    ),
    FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido) ON UPDATE CASCADE,
    FOREIGN KEY (codigo_tipo_alimento, codigo_alimento) REFERENCES Alimentos(codigo_tipo_alimento, codigo_alimento) ON UPDATE CASCADE
);