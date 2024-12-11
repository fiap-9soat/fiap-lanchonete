# Refaz tabela 'Alimentos'

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE Alimentos;
SET FOREIGN_KEY_CHECKS=1;

# Refaz com 'preco_alimento' não nulo
create table Alimentos
(
    codigo_tipo_alimento smallint                    not null,
    codigo_alimento      smallint                    not null,
    nome_alimento        varchar(30)                 not null,
    nome_funci_alter     varchar(20)                 not null,
    ts_alter             timestamp                   not null,
    preco_alimento       decimal(16, 2) default 0.00 not null,
    primary key (codigo_tipo_alimento, codigo_alimento),
    constraint idx2
        unique (nome_alimento),
    constraint Alimentos_ibfk_1
        foreign key (codigo_tipo_alimento) references Alimentos_Tipo (codigo_tipo_alimento)
);

# Re-insere dados anteriores
INSERT INTO Alimentos (
    codigo_tipo_alimento,
    codigo_alimento,
    nome_alimento,
    nome_funci_alter,
    ts_alter
)
VALUES (
           1,
           2,
           "Hamburger",
           "Jonas",
           CURRENT_TIMESTAMP()
       ),
       (
           2,
           1,
           "Fritas",
           "João",
           CURRENT_TIMESTAMP()
       ),
       (
           2,
           2,
           "Nuggets",
           "Jonas",
           CURRENT_TIMESTAMP()
       ),
       (
           3,
           1,
           "Coca",
           "Nagano",
           CURRENT_TIMESTAMP()
       ),
       (
           3,
           2,
           "Água",
           "Jonas",
           CURRENT_TIMESTAMP()
       ),
       (
           4,
           1,
           "Sorvete de Baunilha",
           "Jonas",
           CURRENT_TIMESTAMP()
       ),
       (
           4,
           2,
           "Torta de maçã",
           "Jonas",
           CURRENT_TIMESTAMP()
       );
