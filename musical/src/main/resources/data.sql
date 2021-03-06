DROP TABLE IF EXISTS MUSICA;
DROP TABLE IF EXISTS ARTISTA;
DROP TABLE IF EXISTS ESTILO;


CREATE TABLE ARTISTA(
    ID integer AUTO_INCREMENT not null,
    NOME varchar(255),
    primary key(ID)
);

CREATE TABLE ESTILO(
    ID integer AUTO_INCREMENT not null,
    DESCRICAO VARCHAR(255),
    PRIMARY KEY(ID)
);

CREATE TABLE MUSICA(
    ID integer AUTO_INCREMENT not null,
    NOME VARCHAR(255),
    ESTILO_ID INTEGER NOT NULL,
    ARTISTA_ID INTEGER NOT NULL,
    PRIMARY KEY(ID)
);


INSERT INTO ARTISTA(NOME) VALUES('Artista 1');
INSERT INTO ARTISTA(NOME) VALUES('Artista 2');

INSERT INTO ESTILO(DESCRICAO) VALUES('Estilo 1');
INSERT INTO ESTILO(DESCRICAO) VALUES('Estilo 2');

INSERT INTO MUSICA(NOME, ESTILO_ID, ARTISTA_ID) VALUES('Musica 1', 1, 1);
INSERT INTO MUSICA(NOME, ESTILO_ID, ARTISTA_ID) VALUES('Musica 2', 2, 2);
