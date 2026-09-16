-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into estado (nome, sigla, idregiao) values('Tocantins', 'TO', 3);
insert into estado (nome, sigla, idregiao) values('Goias', 'GO', 1);
insert into estado (nome, sigla, idregiao) values('Rio de Janeiro', 'RJ', 4);
insert into estado (nome, sigla) values('Sao Paulo', 'SP');