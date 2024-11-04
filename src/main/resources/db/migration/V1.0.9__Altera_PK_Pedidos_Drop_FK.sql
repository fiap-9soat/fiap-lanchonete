ALTER TABLE Pedidos DROP FOREIGN KEY Pedidos_ibfk_1;
ALTER TABLE Hist_Pedidos DROP FOREIGN KEY Hist_Pedidos_ibfk_1;
ALTER TABLE Pedidos_Alimentos DROP FOREIGN KEY Pedidos_Alimentos_ibfk_1;
ALTER TABLE Hist_Pedidos_Alimentos DROP FOREIGN KEY Hist_Pedidos_Alimentos_ibfk_1;
ALTER TABLE Pedidos DROP PRIMARY KEY;
ALTER TABLE Hist_Pedidos DROP PRIMARY KEY;
ALTER TABLE Pedidos
ADD PRIMARY KEY (codigo_pedido);
ALTER TABLE Hist_Pedidos
ADD PRIMARY KEY (codigo_pedido);
ALTER TABLE Pedidos
MODIFY codigo_cliente INT NULL;
ALTER TABLE Hist_Pedidos
MODIFY codigo_cliente INT NULL;
CREATE UNIQUE INDEX pedidos_clientes ON Pedidos (codigo_pedido, codigo_cliente);
CREATE UNIQUE INDEX pedidos_clientes ON Hist_Pedidos (codigo_pedido, codigo_cliente);
ALTER TABLE Pedidos
ADD CONSTRAINT pedido_cliente_fk1 FOREIGN KEY (codigo_cliente) REFERENCES Clientes(codigo_cliente);
ALTER TABLE Hist_Pedidos
ADD CONSTRAINT hist_ped_cliente_fk2 FOREIGN KEY (codigo_cliente) REFERENCES Clientes(codigo_cliente);
ALTER TABLE Pedidos_Alimentos
ADD CONSTRAINT pedido_alimentos_fk1 FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido);
ALTER TABLE Hist_Pedidos_Alimentos
ADD CONSTRAINT pedido_alimentos_fk2 FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido);