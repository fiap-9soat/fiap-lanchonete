alter table Alimentos_Tipo
    modify codigo_tipo_alimento INT SIGNED not null;

alter table Alimentos_Tipo
    drop primary key;

alter table Alimentos_Tipo
    add primary key (codigo_tipo_alimento);

alter table Alimentos_Tipo
    add constraint Alimentos_tipo_uni_nome
        unique (nome_tipo_alimento);

