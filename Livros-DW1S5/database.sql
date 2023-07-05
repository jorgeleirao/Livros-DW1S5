CREATE DATABASE biblioteca;
USE biblioteca;
create table if not exists usuarios(
	id int not null auto_increment,
    login varchar(30) not null,
    password varchar(300) not null,
    nome varchar(30) not null,
    email varchar(30),
    primary key(id),
    unique(login)
);

CREATE TABLE if not exists livros ( id int(11) NOT NULL AUTO_INCREMENT, titulo varchar(200) NOT NULL, autor varchar(200) NOT NULL, genero varchar(100) NOT NULL, ano int(4) NOT NULL, PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;