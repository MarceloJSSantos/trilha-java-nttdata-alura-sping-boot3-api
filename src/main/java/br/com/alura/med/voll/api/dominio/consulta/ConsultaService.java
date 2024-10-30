package br.com.alura.med.voll.api.dominio.consulta;

import br.com.alura.med.voll.api.dominio.exceptions.ValidacaoNegocioException;
import br.com.alura.med.voll.api.dominio.medico.Medico;
import br.com.alura.med.voll.api.dominio.medico.MedicoRepository;
import br.com.alura.med.voll.api.dominio.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void agendaConsulta(ConsultaDadosAgendamento dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoNegocioException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoNegocioException("Id do médico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        var consulta = new Consulta(null, paciente, medico, dados.dataHora(), null);

        consultaRepository.save(consulta);

    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoNegocioException("Id da consulta informado não existe!");
        }

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
