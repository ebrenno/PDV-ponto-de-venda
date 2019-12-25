SET FOREIGN_KEY_CHECKS = 0; 
truncate table venda;
truncate table desconto;
truncate table produto;
truncate table localidade;
truncate table cliente;
SET FOREIGN_KEY_CHECKS = 1;  

insert into cliente(nome,bonus)values
('bruno',300),
('ricardo',600),
('leo',400),
('julio',100),
('eduardo',900),
('rafaela',700),
('carla',0);

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
