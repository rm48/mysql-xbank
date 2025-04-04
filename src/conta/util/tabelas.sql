CREATE DATABASE sisbank;

USE sisbank;

CREATE TABLE cliente(
	id_cliente int primary key auto_increment,
	nome varchar(30),
	saldo char(11),
	credito char(9),
	senha char(4)
);

CREATE TABLE resultados(
	id_concurso int primary key auto_increment,
	numeros char(17),
	acertos varchar(17),
	premio char(9)
);