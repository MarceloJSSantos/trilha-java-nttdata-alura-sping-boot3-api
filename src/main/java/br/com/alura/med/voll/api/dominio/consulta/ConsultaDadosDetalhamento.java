package br.com.alura.med.voll.api.dominio.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConsultaDadosDetalhamento(
        Long id,
        Long idPaciente,
        Long idMedico,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora) {
    public ConsultaDadosDetalhamento(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(),consulta.getDataHora());
    }
}
