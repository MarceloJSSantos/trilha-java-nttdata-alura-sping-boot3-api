package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import br.com.alura.med.voll.api.dominio.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository repository;
    public void validar(ConsultaDadosAgendamento dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo){
            throw new ValidacaoNegocioException("Consulta não pode ser agendada com paciente inativo!");
        }
    }
}
