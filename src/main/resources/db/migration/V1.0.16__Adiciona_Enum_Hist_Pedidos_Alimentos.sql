alter table Hist_Pedidos_Alimentos
    modify column tipo_alter enum ('A','D','I');

alter table Alimentos
    alter column preco_alimento set default 0.0;
