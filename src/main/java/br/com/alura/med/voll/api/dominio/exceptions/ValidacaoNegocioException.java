package br.com.alura.med.voll.api.dominio.exceptions;

public class ValidacaoNegocioException extends RuntimeException {
    public ValidacaoNegocioException(String mensagem) {
        super(mensagem);
    }
}
