create database loja;
use loja;


create table tipoUsuario(
id_tipoUsuario bigint primary key,
nome varchar(255)
);

insert into tipoUsuario(id_tipoUsuario, nome) values
(1, 'Administrador'),
(2, 'Vendedor');

create table usuario(
id_usuario bigint primary key,
nome varchar(255),
email varchar(255),
senha varchar(255),
id_tipoUsuario bigint,
foreign key(id_tipoUsuario) references tipoUsuario(id_tipoUsuario)
);

insert into usuario(id_usuario, nome, email, senha, id_tipoUsuario) values
(1, 'admin1', 'admin1@email.com', '$2a$10$cdwwbspUOiif1bwmr8dtPuEAFsyzGnLNqIwyHrPJ4lqs4/r.Zesp.', 1);


create table cliente(
id_cliente bigint primary key,
nome varchar(255),
email varchar(255),
endereco varchar(255),
telefone varchar(20),
obervacao_cliente varchar(255)
);

create table produto(
id_produto bigint primary key,
nome varchar(255),
descricao varchar(255),
preco_venda numeric(15,2),
preco_compra numeric(15,2),
quantidade int,
id_categoria bigint,
foreign key(id_categoria) references categoria(id_categoria)
);

create table categoria(
id_categoria bigint primary key,
nome varchar(255)
);

create table pedido(
id_pedido bigint primary key,
id_cliente bigint,
data_pedido datetime,
observacao_pedido varchar(255),
valor_pedido numeric(15,2),
foreign key(id_cliente) references cliente(id_cliente)
);

select * from pedido;
create table itemPedido(
id_itemPedido bigint primary key,
id_pedido bigint,
id_produto bigint,
quantidade int,
valor_venda numeric(15,2),
foreign key(id_pedido) references pedido(id_pedido),
foreign key(id_produto) references produto(id_produto)
);

delete from itemPedido;
select * from tipoUsuario




