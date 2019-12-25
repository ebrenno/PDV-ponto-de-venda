drop database if exists ponto_de_venda_teste;
create database ponto_de_venda_teste;
use ponto_de_venda_teste;

CREATE TABLE cliente (
    codcli INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(35),
    bonus INT,
    perfil VARCHAR(1),
    status VARCHAR(1),
    PRIMARY KEY (codcli)
);

CREATE TABLE localidade (
    codlocal INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(35),
    endereco VARCHAR(80),
    telefone VARCHAR(14),
    PRIMARY KEY (codlocal)
);

CREATE TABLE produto (
    codprod INT NOT NULL AUTO_INCREMENT,
    codlocal INT,
    descricao VARCHAR(35),
    qtd_estoque INT,
    preco_unitario DOUBLE,
    FOREIGN KEY (codlocal)
        REFERENCES localidade (codlocal),
    PRIMARY KEY (codprod)
);

CREATE TABLE venda (
    codcli INT NOT NULL,
    codprod INT NOT NULL,
    codlocal INT NOT NULL,
    qtd_venda INT,
    valor_total DOUBLE,
    data_venda DATE,
    FOREIGN KEY (codcli)
        REFERENCES cliente (codcli),
    FOREIGN KEY (codprod)
        REFERENCES produto (codprod),
    FOREIGN KEY (codlocal)
        REFERENCES localidade (codlocal),
    PRIMARY KEY (codcli , codprod , codlocal)
);

CREATE TABLE desconto (
    id_desconto INT NOT NULL AUTO_INCREMENT,
    codprod INT,
    percentual INT,
    qtd_min INT,
    qtd_max INT,
    FOREIGN KEY (codprod)
        REFERENCES produto (codprod),
    PRIMARY KEY (id_desconto)
);
