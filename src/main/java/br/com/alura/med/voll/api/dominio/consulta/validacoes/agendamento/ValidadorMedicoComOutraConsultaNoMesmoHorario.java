package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaRepository;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDadosAgendamento dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataHoraAndMotivoCancelamentoIsNull(dados.idMedico(), dados.dataHora());
        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoNegocioException("O médico já possui consulta agendada nesse dia e horário!");
        }

    }
}
