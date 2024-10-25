package br.com.alura.med.voll.api.dominio.medico;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.validation.constraints.NotNull;

public record MedicoDadosParaAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       EnderecoDados endereco) {
}
