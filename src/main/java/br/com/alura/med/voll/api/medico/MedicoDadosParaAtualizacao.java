package br.com.alura.med.voll.api.medico;

import br.com.alura.med.voll.api.endereco.EnderecoDados;
import jakarta.validation.constraints.NotNull;

public record MedicoDadosParaAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       EnderecoDados endereco) {
}
