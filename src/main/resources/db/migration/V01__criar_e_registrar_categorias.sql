CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Peças');
INSERT INTO categoria (nome) values ('Serviços');
INSERT INTO categoria (nome) values ('Eletronicos');
INSERT INTO categoria (nome) values ('Objetos');
INSERT INTO categoria (nome) values ('Outros');