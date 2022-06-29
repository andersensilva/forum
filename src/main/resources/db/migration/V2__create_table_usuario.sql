create table usuario(
    id bigint not null auto_increment,
    nome varchar(50) ,
    email varchar(50) not null,
    primary key(id)
);

insert into usuario values (1, 'Andersen Silva', 'andersen.silva@privacytools.com.br');