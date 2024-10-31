package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaRepository;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteComOutraConsultaNoDia implements ValidadorAgendamentoConsulta {
    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDadosAgendamento dados){
        var primeiroHorario = dados.dataHora().withHour(7);
        var ultimoHorario = dados.dataHora().withHour(18);
        var pacientePossuiOutraConsultaNoMesmoHorario = repository.existsByPacienteIdAndDataHoraBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoNegocioException("Paciente já possui consulta agendada nesse dia!");
        }
    }
}
