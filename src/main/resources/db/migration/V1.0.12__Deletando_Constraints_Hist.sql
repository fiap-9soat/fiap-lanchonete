ALTER TABLE Hist_Pedidos DROP FOREIGN KEY hist_ped_cliente_fk2;
ALTER TABLE Hist_Pedidos DROP PRIMARY KEY;
ALTER TABLE Hist_Pedidos
ADD PRIMARY KEY (codigo_pedido, ts_alter);
ALTER TABLE Hist_Pedidos DROP CONSTRAINT pedidos_clientes;