CREATE TABLE Alimentos
(
    codigo_tipo_alimento SMALLINT                 NOT NULL,
    codigo_alimento      SMALLINT                 NOT NULL,
    nome_alimento        VARCHAR(255)             NULL,
    nome_funci_alter     VARCHAR(255)             NULL,
    preco_alimento       DECIMAL(16, 2) DEFAULT 0 NULL,
    ts_alter             datetime                 NULL,
    CONSTRAINT pk_alimentos PRIMARY KEY (codigo_tipo_alimento, codigo_alimento)
);

CREATE TABLE Alimentos_Tipo
(
    codigo_tipo_alimento SMALLINT     NOT NULL,
    nome_tipo_alimento   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_alimentos_tipo PRIMARY KEY (codigo_tipo_alimento, nome_tipo_alimento)
);

CREATE TABLE Clientes
(
    codigo_cliente INT AUTO_INCREMENT NOT NULL,
    cpf            VARCHAR(11)        NOT NULL,
    nome           VARCHAR(14)        NOT NULL,
    sobrenome      VARCHAR(16)        NOT NULL,
    email          VARCHAR(90)        NOT NULL,
    CONSTRAINT pk_clientes PRIMARY KEY (codigo_cliente)
);

CREATE TABLE Hist_Pedidos
(
    codigo_pedido    INT      NOT NULL,
    ts_alter         datetime NOT NULL,
    codigo_cliente   INT      NULL,
    ts_ultimo_pedido datetime NULL,
    estado_pedido    SMALLINT NULL,
    CONSTRAINT pk_hist_pedidos PRIMARY KEY (codigo_pedido, ts_alter)
);

CREATE TABLE Hist_Pedidos_Alimentos
(
    codigo_pedido        INT          NOT NULL,
    codigo_tipo_alimento SMALLINT     NOT NULL,
    codigo_alimento      SMALLINT     NOT NULL,
    ts_alter             datetime     NOT NULL,
    qtdade_alimento      SMALLINT     NULL,
    tipo_alter           VARCHAR(255) NULL,
    CONSTRAINT pk_hist_pedidos_alimentos PRIMARY KEY (codigo_pedido, codigo_tipo_alimento, codigo_alimento, ts_alter)
);

CREATE TABLE Pedidos
(
    codigo_pedido     INT AUTO_INCREMENT NOT NULL,
    codigo_cliente    INT                NULL,
    ts_ultimo_pedido  timestamp          NOT NULL,
    estado_pedido     SMALLINT           NOT NULL,
    estado_pagamento  CHAR               NULL,
    codigo_id_externo CHAR(255)          NULL,
    CONSTRAINT pk_pedidos PRIMARY KEY (codigo_pedido)
);

CREATE TABLE Pedidos_Alimentos
(
    codigo_pedido        INT      NOT NULL,
    codigo_tipo_alimento SMALLINT NOT NULL,
    codigo_alimento      SMALLINT NOT NULL,
    qtdade_alimento      SMALLINT NULL,
    CONSTRAINT pk_pedidos_alimentos PRIMARY KEY (codigo_pedido, codigo_tipo_alimento, codigo_alimento)
);

ALTER TABLE Pedidos_Alimentos
    ADD CONSTRAINT FK_PEDIDOS_ALIMENTOS_ON_CODIGO_PEDIDO FOREIGN KEY (codigo_pedido) REFERENCES Pedidos (codigo_pedido);

ALTER TABLE Pedidos_Alimentos
    ADD CONSTRAINT FK_PEDIDOS_ALIMENTOS_ON_COTIALCOAL FOREIGN KEY (codigo_tipo_alimento, codigo_alimento) REFERENCES Alimentos (codigo_tipo_alimento, codigo_alimento);

ALTER TABLE Pedidos
    ADD CONSTRAINT FK_PEDIDOS_ON_CODIGO_CLIENTE FOREIGN KEY (codigo_cliente) REFERENCES Clientes (codigo_cliente);
