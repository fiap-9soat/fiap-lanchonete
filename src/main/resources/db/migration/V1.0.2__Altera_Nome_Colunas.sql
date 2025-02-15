--  DROP FKs
ALTER TABLE Pedidos_Produtos DROP FOREIGN KEY FK_PEDIDOS_ALIMENTOS_ON_CODIGO_PEDIDO;
ALTER TABLE Pedidos_Produtos DROP FOREIGN KEY FK_PEDIDOS_ALIMENTOS_ON_COTIALCOAL;
ALTER TABLE Pedidos DROP FOREIGN KEY FK_PEDIDOS_ON_CODIGO_CLIENTE;
-- Alterar tabela ALIMENTOS
ALTER TABLE Produtos DROP PRIMARY KEY;
ALTER TABLE Produtos
    RENAME COLUMN codigo_tipo_produto TO codigo_tipo_produto;
ALTER TABLE Produtos
    RENAME COLUMN codigo_produto TO codigo_produto;
ALTER TABLE Produtos
    RENAME COLUMN nome_produto TO nome_produto;
ALTER TABLE Produtos
    RENAME COLUMN preco_produto TO preco_produto;
ALTER TABLE Produtos
ADD CONSTRAINT pk_produtos PRIMARY KEY (codigo_tipo_produto, codigo_produto);
--  Alterar tabela Produtos_Tipo
ALTER TABLE Produtos_Tipo DROP PRIMARY KEY;
ALTER TABLE Produtos_Tipo
    RENAME COLUMN codigo_tipo_produto TO codigo_tipo_produto;
ALTER TABLE Produtos_Tipo
    RENAME COLUMN nome_tipo_produto TO nome_tipo_produto;
ALTER TABLE Produtos_Tipo
ADD CONSTRAINT pk_produtos_tipo PRIMARY KEY (codigo_tipo_produto, nome_tipo_produto);
-- Alterar tabela Hist_Pedidos_Produtos
ALTER TABLE Hist_Pedidos_Produtos DROP PRIMARY KEY;
ALTER TABLE Hist_Pedidos_Produtos
    RENAME COLUMN codigo_tipo_produto TO codigo_tipo_produto;
ALTER TABLE Hist_Pedidos_Produtos
    RENAME COLUMN codigo_produto TO codigo_produto;
ALTER TABLE Hist_Pedidos_Produtos
    RENAME COLUMN qtdade_produto TO qtdade_produto;
ALTER TABLE Hist_Pedidos_Produtos
ADD CONSTRAINT pk_hist_pedidos_produtos PRIMARY KEY (
        codigo_pedido,
        codigo_tipo_produto,
        codigo_produto,
        ts_alter
    );
-- Alterar tabela Pedidos_Produtos
ALTER TABLE Pedidos_Produtos DROP PRIMARY KEY;
ALTER TABLE Pedidos_Produtos
    RENAME COLUMN codigo_tipo_produto TO codigo_tipo_produto;
ALTER TABLE Pedidos_Produtos
    RENAME COLUMN codigo_produto TO codigo_produto;
ALTER TABLE Pedidos_Produtos
    RENAME COLUMN qtdade_produto TO qtdade_produto;
ALTER TABLE Pedidos_Produtos
ADD CONSTRAINT pk_pedidos_produtos PRIMARY KEY (
        codigo_pedido,
        codigo_tipo_produto,
        codigo_produto
    );
-- Add FKs
ALTER TABLE Produtos
ADD CONSTRAINT FK_PEDIDOS_TIPO_ON_PEDIDOS FOREIGN KEY (codigo_tipo_produto) REFERENCES Produtos_Tipo (codigo_tipo_produto);
ALTER TABLE Pedidos_Produtos
ADD CONSTRAINT FK_PEDIDOS_PRODUTOS_ON_CODIGO_PEDIDO FOREIGN KEY (codigo_pedido) REFERENCES Pedidos (codigo_pedido);
ALTER TABLE Pedidos_Produtos
ADD CONSTRAINT FK_PEDIDOS_Produtos_ON_CODIGO_ALI FOREIGN KEY (codigo_tipo_produto, codigo_produto) REFERENCES Produtos (codigo_tipo_produto, codigo_produto);
ALTER TABLE Pedidos
ADD CONSTRAINT FK_PEDIDOS_ON_CODIGO_CLIENTE FOREIGN KEY (codigo_cliente) REFERENCES Clientes (codigo_cliente);