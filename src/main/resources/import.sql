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

insert into pessoa (nome, cpf, email) values ('Ana Clara Souza', '12345678901', 'ana.clara@email.com');
insert into pessoa (nome, cpf, email) values ('Bruno Martins Lima', '12345678902', 'bruno.martins@email.com');
insert into pessoa (nome, cpf, email) values ('Carla Fernanda Alves', '12345678903', 'carla.alves@email.com');
insert into pessoa (nome, cpf, email) values ('Diego Pereira Rocha', '12345678904', 'diego.rocha@email.com');
insert into pessoa (nome, cpf, email) values ('Elisa Gomes Santos', '12345678905', 'elisa.santos@email.com');
insert into pessoa (nome, cpf, email) values ('Fabiana Costa Melo', '98765432101', 'fabiana.melo@email.com');
insert into pessoa (nome, cpf, email) values ('Gustavo Henrique Dias', '98765432102', 'gustavo.dias@email.com');
insert into pessoa (nome, cpf, email) values ('Helena Ribeiro Castro', '98765432103', 'helena.castro@email.com');
insert into pessoa (nome, cpf, email) values ('Igor Carvalho Nunes', '98765432104', 'igor.nunes@email.com');
insert into pessoa (nome, cpf, email) values ('Juliana Mendes Araujo', '98765432105', 'juliana.mendes@email.com');

insert into paciente (id, telefone, endereco) values (1, '63991234567', 'Rua das Palmeiras, 120');
insert into paciente (id, telefone, endereco) values (2, '63992345678', 'Avenida Tocantins, 450');
insert into paciente (id, telefone, endereco) values (3, '63993456789', 'Quadra 204 Sul, Alameda 10');
insert into paciente (id, telefone, endereco) values (4, '63994567890', 'Rua Rio Branco, 88');
insert into paciente (id, telefone, endereco) values (5, '63995678901', 'Avenida JK, 1500');

insert into psicologo (id, crp) values (6, 'CRP-23/1001');
insert into psicologo (id, crp) values (7, 'CRP-23/1002');
insert into psicologo (id, crp) values (8, 'CRP-23/1003');
insert into psicologo (id, crp) values (9, 'CRP-23/1004');
insert into psicologo (id, crp) values (10, 'CRP-23/1005');

alter sequence if exists pessoa_id_seq restart with 11;