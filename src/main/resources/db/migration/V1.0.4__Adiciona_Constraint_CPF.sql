alter table Clientes
    add constraint Clientes_cpf_key
        unique (cpf);