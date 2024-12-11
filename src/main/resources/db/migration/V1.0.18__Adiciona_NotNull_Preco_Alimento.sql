UPDATE Alimentos SET preco_alimento = 0.00 WHERE preco_alimento IS NULL;

alter table Alimentos
    modify preco_alimento decimal(16, 2) default 0.00 not null;
