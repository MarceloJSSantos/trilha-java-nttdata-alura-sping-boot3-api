package br.com.alura.med.voll.api.paciente;

import br.com.alura.med.voll.api.endereco.EnderecoDados;
import jakarta.validation.constraints.NotNull;

public record PacienteDadosAtualizacao(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDados endereco
) {
}
