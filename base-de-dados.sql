-- Gerado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   em:        2024-09-23 18:02:20 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



DROP TABLE t_cs_bairro CASCADE CONSTRAINTS;

DROP TABLE t_cs_carro CASCADE CONSTRAINTS;

DROP TABLE t_cs_cidade CASCADE CONSTRAINTS;

DROP TABLE t_cs_estado CASCADE CONSTRAINTS;

DROP TABLE t_cs_logradouro CASCADE CONSTRAINTS;

DROP TABLE t_cs_problemas CASCADE CONSTRAINTS;

DROP TABLE t_cs_telefone_usuario CASCADE CONSTRAINTS;

DROP TABLE t_cs_usuario CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE t_cs_bairro (
    id_bairro             NUMBER(3) NOT NULL,
    t_cs_cidade_id_cidade NUMBER(4) NOT NULL,
    nm_bairro             VARCHAR2(45) NOT NULL,
    nm_zona_bairro        VARCHAR2(20) NOT NULL
);

ALTER TABLE t_cs_bairro ADD CONSTRAINT t_cs_bairro_pk PRIMARY KEY ( id_bairro );

CREATE TABLE t_cs_carro (
    id_carro                NUMBER(3) NOT NULL,
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    mc_carro                VARCHAR2(20) NOT NULL,
    md_carro                VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN t_cs_carro.id_carro IS
    'Este é o ID do carro do usuário';

COMMENT ON COLUMN t_cs_carro.mc_carro IS
    'Esse é o atributo onde fica a marca do carro';

COMMENT ON COLUMN t_cs_carro.md_carro IS
    'Este é o atributo onde fica o modelo do carro';

ALTER TABLE t_cs_carro ADD CONSTRAINT t_cs_carro_pk PRIMARY KEY ( id_carro );

CREATE TABLE t_cs_cidade (
    id_cidade             NUMBER(4) NOT NULL,
    t_cs_estado_id_estado NUMBER(3) NOT NULL,
    nm_cidade             VARCHAR2(45) NOT NULL,
    nr_ddd                NUMBER(3) NOT NULL
);

COMMENT ON COLUMN t_cs_cidade.id_cidade IS
    'Este é o atributo onde fica o id da cidade';

COMMENT ON COLUMN t_cs_cidade.nm_cidade IS
    'Este é o atributo onde fica o nome da cidade';

COMMENT ON COLUMN t_cs_cidade.nr_ddd IS
    'Este é o atributo onde fica o ddd da cidade';

ALTER TABLE t_cs_cidade ADD CONSTRAINT t_cs_cidade_pk PRIMARY KEY ( id_cidade );

CREATE TABLE t_cs_estado (
    id_estado NUMBER(3) NOT NULL,
    sg_estado CHAR(2) NOT NULL,
    nm_estado VARCHAR2(30) NOT NULL
);

ALTER TABLE t_cs_estado ADD CONSTRAINT t_cs_estado_pk PRIMARY KEY ( id_estado );

CREATE TABLE t_cs_logradouro (
    id_logradouro           NUMBER(3) NOT NULL,
    t_cs_bairro_id_bairro   NUMBER(3) NOT NULL,
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    nm_logradouro           VARCHAR2(30) NOT NULL,
    nr_logradouro           NUMBER(7) NOT NULL,
    nr_cep                  NUMBER(8) NOT NULL
);

COMMENT ON COLUMN t_cs_logradouro.id_logradouro IS
    'Este é o atributo onde fica o id do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nm_logradouro IS
    'Este é o atributo onde fica o nome do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nr_logradouro IS
    'Este é o atributo onde fica o número do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nr_cep IS
    'Este é o atributo onde fica o número do cep do usuário';

ALTER TABLE t_cs_logradouro ADD CONSTRAINT t_cs_logradouro_pk PRIMARY KEY ( id_logradouro );

CREATE TABLE t_cs_problemas (
    id_problema         NUMBER(3) NOT NULL,
    t_cs_carro_id_carro NUMBER(3) NOT NULL,
    vl_problema         NUMBER(5, 2) NOT NULL,
    nm_problema         VARCHAR2(20) NOT NULL,
    tp_peca_problema    VARCHAR2(20) NOT NULL,
    dc_problema         VARCHAR2(30)
);

ALTER TABLE t_cs_problemas
    ADD CONSTRAINT ck_cs_vl_problema_carro CHECK ( vl_problema BETWEEN 100 AND 9999 );

COMMENT ON COLUMN t_cs_problemas.id_problema IS
    'Este é o atributo onde fica o id do problema.';

COMMENT ON COLUMN t_cs_problemas.vl_problema IS
    'Esté é o valor do problema do carro, tem no máximo 5 digitos e 2 casas decimais';

COMMENT ON COLUMN t_cs_problemas.nm_problema IS
    'Este é o nome do problema, exemplo (Óleo velho)';

COMMENT ON COLUMN t_cs_problemas.tp_peca_problema IS
    'Este é o atributo onde fica o tipo de peça onde está o problema';

COMMENT ON COLUMN t_cs_problemas.dc_problema IS
    'Este é o atributo onde fica a descrção do atributo, é opcional.';

ALTER TABLE t_cs_problemas ADD CONSTRAINT t_cs_problemas_pk PRIMARY KEY ( id_problema );

CREATE TABLE t_cs_telefone_usuario (
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    id_telefone             NUMBER(3) NOT NULL,
    nr_ddi                  NUMBER(3) NOT NULL,
    nr_ddd                  NUMBER(3) NOT NULL,
    nr_telefone             NUMBER(10) NOT NULL,
    tp_telefone             VARCHAR2(20) NOT NULL,
    st_telefone             VARCHAR2(15) NOT NULL
);

ALTER TABLE t_cs_telefone_usuario
    ADD CHECK ( tp_telefone IN ( 'Celular', 'Fixo' ) );

ALTER TABLE t_cs_telefone_usuario
    ADD CONSTRAINT ck_cs_status_telefone_paciente CHECK ( st_telefone IN ( 'A', 'I' ) );

COMMENT ON COLUMN t_cs_telefone_usuario.id_telefone IS
    'Este é o atributo onde fica o telefone do usuario';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_ddi IS
    'Este é o atributo onde fica o DDI do telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_ddd IS
    'Este é o atributo onde fica o DDD do telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_telefone IS
    'Este é o atributo onde fica o número de telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.tp_telefone IS
    'Este é o atributo onde fica o tipo de telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.st_telefone IS
    'Este é o atributo onde fica o status do telefone do usuário';

ALTER TABLE t_cs_telefone_usuario ADD CONSTRAINT t_cs_telefone_usuario_pk PRIMARY KEY ( id_telefone,
                                                                                        t_cs_usuario_id_usuario );

CREATE TABLE t_cs_usuario (
    id_usuario NUMBER(3) NOT NULL,
    us_cpf     NUMBER(11) NOT NULL,
    nm_usuario VARCHAR2(40) NOT NULL
);

COMMENT ON COLUMN t_cs_usuario.id_usuario IS
    'Este é o atributo onde fica o ID do usuário.';

COMMENT ON COLUMN t_cs_usuario.us_cpf IS
    'Esté é o atributo onde fica o cpf do usuário';

COMMENT ON COLUMN t_cs_usuario.nm_usuario IS
    'Esté o atributo onde fica o nome do usuário.';

ALTER TABLE t_cs_usuario ADD CONSTRAINT t_cs_usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE t_cs_usuario ADD CONSTRAINT t_cs_usuario_us_cpf_un UNIQUE ( us_cpf );

ALTER TABLE t_cs_bairro
    ADD CONSTRAINT t_cs_bairro_t_cs_cidade_fk FOREIGN KEY ( t_cs_cidade_id_cidade )
        REFERENCES t_cs_cidade ( id_cidade );

ALTER TABLE t_cs_carro
    ADD CONSTRAINT t_cs_carro_t_cs_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );

ALTER TABLE t_cs_cidade
    ADD CONSTRAINT t_cs_cidade_t_cs_estado_fk FOREIGN KEY ( t_cs_estado_id_estado )
        REFERENCES t_cs_estado ( id_estado );

ALTER TABLE t_cs_logradouro
    ADD CONSTRAINT t_cs_logradouro_t_cs_bairro_fk FOREIGN KEY ( t_cs_bairro_id_bairro )
        REFERENCES t_cs_bairro ( id_bairro );

ALTER TABLE t_cs_logradouro
    ADD CONSTRAINT t_cs_logradouro_t_cs_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );

ALTER TABLE t_cs_problemas
    ADD CONSTRAINT t_cs_problemas_t_cs_carro_fk FOREIGN KEY ( t_cs_carro_id_carro )
        REFERENCES t_cs_carro ( id_carro );

ALTER TABLE t_cs_telefone_usuario
    ADD CONSTRAINT t_cs_telefone_usuario_t_cs_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );



