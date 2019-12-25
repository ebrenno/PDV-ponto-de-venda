drop database if exists ponto_de_venda;
create database ponto_de_venda;
use ponto_de_venda;

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

insert into cliente(nome,bonus)values
('bruno',300),
('ricardo',600),
('leo',400),
('julio',100),
('eduardo',900),
('rafaela',700);

insert into localidade(nome)values
('rio de janeiro'),
('sao paulo'),
('minas gerais'),
('bahia');

insert into produto(codlocal,descricao,qtd_estoque,preco_unitario)values
(4,'borracha',15,2.80),
(1,'lápis',22,1.20),
(1,'caderno',22,10.00),
(2,'tesoura escolar',7,5.10);
insert into desconto(codprod,percentual,qtd_min,qtd_max) values
(2,5,3,9),
(1,10,5,10),
(4,35,1,3),
(3,15,3,8),
(2,30,11,20);