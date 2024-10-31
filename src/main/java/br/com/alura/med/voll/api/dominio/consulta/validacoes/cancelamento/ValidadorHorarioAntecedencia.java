package br.com.alura.med.voll.api.dominio.consulta.validacoes.cancelamento;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaRepository;
import br.com.alura.med.voll.api.dominio.consulta.DadosCancelamentoConsulta;
import br.com.alura.med.voll.api.infra.exceptions.ValidacaoNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getDataHora()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoNegocioException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
