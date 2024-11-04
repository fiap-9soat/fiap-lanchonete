ALTER TABLE Hist_Pedidos_Alimentos DROP FOREIGN KEY Hist_Pedidos_Alimentos_ibfk_2;
ALTER TABLE Hist_Pedidos_Alimentos DROP FOREIGN KEY pedido_alimentos_fk2;
ALTER TABLE Hist_Pedidos_Alimentos DROP PRIMARY KEY;
ALTER TABLE Hist_Pedidos_Alimentos
ADD PRIMARY KEY (
        codigo_pedido,
        codigo_tipo_alimento,
        codigo_alimento,
        ts_alter
    );