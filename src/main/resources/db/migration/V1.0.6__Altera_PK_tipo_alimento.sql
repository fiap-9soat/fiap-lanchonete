alter table Alimentos
    modify codigo_alimento smallint auto_increment;

alter table Alimentos
    auto_increment = 1;

alter table Alimentos_Tipo
    drop primary key;

alter table Alimentos_Tipo
    add primary key (codigo_tipo_alimento);

alter table Alimentos_Tipo
    add constraint Alimentos_tipo_uni_nome
        unique (nome_tipo_alimento);

