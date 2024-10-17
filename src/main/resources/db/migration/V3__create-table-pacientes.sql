CREATE TABLE pacientes (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(120) NULL,
   email VARCHAR(100) NULL,
   cpf VARCHAR(14) NULL,
   telefone VARCHAR(20) NULL,
   logradouro VARCHAR(120) NULL,
   bairro VARCHAR(100) NULL,
   cep VARCHAR(9) NULL,
   cidade VARCHAR(100) NULL,
   uf CHAR(2) NULL,
   numero VARCHAR(20) NULL,
   complemento VARCHAR(100) NULL,
   CONSTRAINT pk_pacientes PRIMARY KEY (id)
);