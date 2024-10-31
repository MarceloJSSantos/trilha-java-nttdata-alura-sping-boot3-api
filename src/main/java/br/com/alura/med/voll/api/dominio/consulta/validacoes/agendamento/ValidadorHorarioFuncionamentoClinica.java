package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{
    public void validar(ConsultaDadosAgendamento dados){
        var dataHoraConsulta = dados.dataHora();

        var eDomingo = dataHoraConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var eAntesDaAberturaDaClinica = dataHoraConsulta.getHour() < 7;
        var eDepoisDoFechamentoDaClinica = dataHoraConsulta.getHour() > 18;

        if(eDomingo || eAntesDaAberturaDaClinica || eDepoisDoFechamentoDaClinica){
            throw new ValidacaoNegocioException("""
                    Consulta só pode ser agendada no horário de funcionamento da clínica!
                    De 2a a sábado, das 07:00 às 18:00
                    """);
        }
    }
}
