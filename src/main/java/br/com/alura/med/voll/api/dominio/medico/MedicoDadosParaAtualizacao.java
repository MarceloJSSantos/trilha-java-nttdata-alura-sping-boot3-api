package br.com.alura.med.voll.api.dominio.medico;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record MedicoDadosParaAtualizacao(
       @NotNull
       Long id,
       @Column(unique = true)
       String nome,
       String telefone,
       EnderecoDados endereco) {
}
