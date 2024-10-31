package br.com.alura.med.voll.api.dominio.consulta.validacoes.cancelamento;

import br.com.alura.med.voll.api.dominio.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
