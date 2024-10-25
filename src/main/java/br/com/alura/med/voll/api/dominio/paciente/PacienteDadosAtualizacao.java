package br.com.alura.med.voll.api.dominio.paciente;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.validation.constraints.NotNull;

public record PacienteDadosAtualizacao(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDados endereco
) {
}
