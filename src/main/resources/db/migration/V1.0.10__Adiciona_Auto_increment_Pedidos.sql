ALTER TABLE Pedidos_Alimentos DROP FOREIGN KEY pedido_alimentos_fk1;
ALTER TABLE Hist_Pedidos_Alimentos DROP FOREIGN KEY pedido_alimentos_fk2;
ALTER TABLE Pedidos DROP PRIMARY KEY;
ALTER TABLE Pedidos
MODIFY codigo_pedido INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE Pedidos_Alimentos
ADD CONSTRAINT pedido_alimentos_fk1 FOREIGN KEY(codigo_pedido) REFERENCES Pedidos(codigo_pedido);
ALTER TABLE Hist_Pedidos_Alimentos
ADD CONSTRAINT pedido_alimentos_fk2 FOREIGN KEY(codigo_pedido) REFERENCES Pedidos(codigo_pedido);