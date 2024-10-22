ALTER TABLE medicos ADD ativo TINYINT NOT NULL;
UPDATE medicos set ativo = 1;