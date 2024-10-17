ALTER TABLE Alimentos DROP COLUMN nome_tipo_alimento;
CREATE TABLE Rel_Alimentos_Tipo (
    codigo_tipo_alimento SMALLINT NOT NULL,
    nome_tipo_alimento VARCHAR(30) NOT NULL,
    PRIMARY KEY (codigo_tipo_alimento, nome_tipo_alimento)
);
INSERT INTO Rel_Alimentos_Tipo (codigo_tipo_alimento, nome_tipo_alimento)
VALUES (1, "Lanche");
CREATE UNIQUE INDEX idx3 ON Rel_Alimentos_Tipo(codigo_tipo_alimento, nome_tipo_alimento);
ALTER TABLE Alimentos
ADD FOREIGN KEY (codigo_tipo_alimento) REFERENCES Rel_Alimentos_Tipo(codigo_tipo_alimento) ON DELETE CASCADE;