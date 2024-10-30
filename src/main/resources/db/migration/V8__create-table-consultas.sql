CREATE TABLE consultas (
    id BIGINT AUTO_INCREMENT NOT NULL,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_hora datetime NOT NULL,
    CONSTRAINT pk_consultas PRIMARY KEY (id)
);

ALTER TABLE consultas ADD CONSTRAINT fk_consultas_on_medico FOREIGN KEY (medico_id) REFERENCES medicos (id);

ALTER TABLE consultas ADD CONSTRAINT fk_consultas_on_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes (id);