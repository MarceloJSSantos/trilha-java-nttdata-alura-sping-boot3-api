package br.com.alura.med.voll.api.infra.exceptions;

public class ValidacaoNegocioException extends RuntimeException {
    public ValidacaoNegocioException(String mensagem) {
        super(mensagem);
    }
}
