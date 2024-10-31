package br.com.alura.med.voll.api.dominio.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataHoraAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime dataHora);

    boolean existsByPacienteIdAndDataHoraBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
