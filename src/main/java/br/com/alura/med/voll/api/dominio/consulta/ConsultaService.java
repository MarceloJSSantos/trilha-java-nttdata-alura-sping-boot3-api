package br.com.alura.med.voll.api.dominio.consulta;

import br.com.alura.med.voll.api.dominio.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import br.com.alura.med.voll.api.dominio.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import br.com.alura.med.voll.api.dominio.medico.Medico;
import br.com.alura.med.voll.api.dominio.medico.MedicoRepository;
import br.com.alura.med.voll.api.dominio.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public ConsultaDadosDetalhamento agendaConsulta(ConsultaDadosAgendamento dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoNegocioException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoNegocioException("Id do médico informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoNegocioException("Não há médico, com essa especialidade, livre para essa data e horário!");
        }

        var consulta = new Consulta(null, paciente, medico, dados.dataHora(), null);
        consultaRepository.save(consulta);

        return new ConsultaDadosDetalhamento(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoNegocioException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico escolherMedico(ConsultaDadosAgendamento dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoNegocioException("A especialidade deve ser informada, para a escolha de um médico!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaDataHora(dados.especialidade(), dados.dataHora());
    }
}
