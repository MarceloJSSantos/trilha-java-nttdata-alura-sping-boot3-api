ALTER TABLE medicos
ADD UNIQUE (nome),
ADD UNIQUE (crm),
ADD UNIQUE (email);

ALTER TABLE pacientes
ADD UNIQUE (nome),
ADD UNIQUE (cpf),
ADD UNIQUE (email);
