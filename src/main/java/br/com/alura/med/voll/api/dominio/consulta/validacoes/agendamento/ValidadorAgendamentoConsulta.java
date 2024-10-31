package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorAgendamentoConsulta {
    void validar(ConsultaDadosAgendamento dados);
}
