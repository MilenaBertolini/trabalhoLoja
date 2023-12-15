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

create table categoria(
id_categoria bigint primary key,
nome varchar(255)
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

INSERT INTO categoria (id_categoria, nome)
VALUES (1, 'Eletrônicos');


INSERT INTO categoria (id_categoria, nome)
VALUES (2, 'Roupas');

-- Inserção de clientes
INSERT INTO cliente (id_cliente, nome, email, endereco, telefone, obervacao_cliente)
VALUES
(1, 'João Silva', 'joao.silva@email.com', 'Rua A, 123', '(11) 1234-5678', 'Cliente VIP'),
(2, 'Maria Oliveira', 'maria.oliveira@email.com', 'Avenida B, 456', '(22) 9876-5432', 'Cliente desde 2020'),
(3, 'Carlos Santos', 'carlos.santos@email.com', 'Travessa C, 789', '(33) 5555-5555', 'Favor entrar em contato à tarde'),
(4, 'Ana Pereira', 'ana.pereira@email.com', 'Praça D, 987', '(44) 2222-3333', 'Endereço de entrega diferente'),
(5, 'Fernando Lima', 'fernando.lima@email.com', 'Alameda E, 654', '(55) 1234-5678', 'Cliente corporativo'),
(6, 'Lúcia Souza', 'lucia.souza@email.com', 'Avenida F, 321', '(66) 8765-4321', 'Pagamento em cheque'),
(7, 'Ricardo Pereira', 'ricardo.pereira@email.com', 'Rua G, 789', '(77) 4444-5555', 'Sem informações adicionais'),
(8, 'Camila Santos', 'camila.santos@email.com', 'Travessa H, 654', '(88) 1111-2222', 'Cliente em débito'),
(9, 'Gustavo Oliveira', 'gustavo.oliveira@email.com', 'Praça I, 987', '(99) 3333-4444', 'Preferência por contato por e-mail'),
(10, 'Isabel Lima', 'isabel.lima@email.com', 'Alameda J, 321', '(00) 9999-8888', 'Cliente em potencial');

INSERT INTO produto (id_produto, nome, descricao, preco_venda, preco_compra, quantidade, id_categoria) values
(1, 'Produto A', 'Descrição do Produto', 29.99, 15.00, 100, 1),
(2, 'Produto B', 'Descrição do Produto', 59.99, 40.00, 20, 2),
(3, 'Produto C', 'Descrição do Produto', 35.99, 19.00, 79, 1),
(4, 'Produto D', 'Descrição do Produto', 40.99, 23.00, 54, 2),
(5, 'Produto E', 'Descrição do Produto', 69.99, 35.00, 6, 1),
(6, 'Produto F', 'Descrição do Produto', 88.99, 29.00, 59, 2),
(7, 'Produto G', 'Descrição do Produto', 36.99, 25.00, 16, 2),
(8, 'Produto H', 'Descrição do Produto', 94.99, 78.00, 31, 1),
(9, 'Produto J', 'Descrição do Produto', 64.99, 40.00, 9, 2);

