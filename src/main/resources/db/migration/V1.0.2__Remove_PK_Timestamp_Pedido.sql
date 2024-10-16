alter table Pedidos
    drop primary key;

alter table Pedidos
    add primary key (codigo_pedido, codigo_cliente);

