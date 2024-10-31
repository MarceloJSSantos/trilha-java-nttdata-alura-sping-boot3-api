package br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import br.com.alura.med.voll.api.dominio.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository repository;
    public void validar(ConsultaDadosAgendamento dados){
        //como o médico não é obrigatório, pode vir null
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoNegocioException("Consulta não pode ser agendada com médico inativo!");
        }
    }
}
