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
INSERT INTO Clientes (cpf, nome, sobrenome, email)
VALUES (
        "12345678911",
        "José",
        "de Souza",
        "josedesouza@abc.com.br"
    );