package br.com.alura.med.voll.api.dominio.consulta;

import br.com.alura.med.voll.api.dominio.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDadosAgendamento(
        @JsonAlias("id_paciente")
        @NotNull
        Long idPaciente,
        @JsonAlias("id_medico")
        Long idMedico,
        @NotNull @Future @JsonAlias("data_hora") @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora,

        Especialidade especialidade) {
}
