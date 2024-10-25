package br.com.alura.med.voll.api.dominio.medico;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDadosCadastro(
       @NotBlank
       String nome,
       @NotBlank @Pattern(regexp = "\\d{4,7}")
       String crm,
       @NotBlank @Email
       String email,
       @NotBlank
       String telefone,
       @NotNull
       Especialidade especialidade,
       @NotNull @Valid
       EnderecoDados endereco) {
}
