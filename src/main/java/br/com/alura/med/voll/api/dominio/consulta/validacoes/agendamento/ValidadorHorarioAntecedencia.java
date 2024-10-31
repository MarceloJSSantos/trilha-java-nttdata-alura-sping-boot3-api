package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{
    public void validar(ConsultaDadosAgendamento dados){
        var dataHoraConsulta = dados.dataHora();
        var dataHoraAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataHoraAgora, dataHoraConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoNegocioException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
