package br.com.alura.med.voll.api.medico;

import br.com.alura.med.voll.api.endereco.EnderecoDados;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDadosAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       EnderecoDados endereco) {
}
