package br.com.alura.med.voll.api.dominio.consulta;

import java.time.LocalDateTime;

public record ConsultaDadosDetalhamento(Long id, Long idPaciente, Long idMedico, LocalDateTime dataHora) {
}
