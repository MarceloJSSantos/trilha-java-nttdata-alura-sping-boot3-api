package br.com.alura.med.voll.api.dominio.paciente;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record PacienteDadosParaAtualizacao(
        @NotNull
        Long id,
        @Column(unique = true)
        String nome,
        String telefone,
        EnderecoDados endereco
) {
}
