package br.com.alura.med.voll.api.dominio.usuario;

import jakarta.persistence.Column;

public record DadosAutenticacao(
        String login,
        String senha) {
}
