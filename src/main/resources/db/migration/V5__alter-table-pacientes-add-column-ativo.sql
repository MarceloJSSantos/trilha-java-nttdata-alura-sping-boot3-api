ALTER TABLE pacientes ADD ativo TINYINT NOT NULL;
UPDATE pacientes set ativo = 1;