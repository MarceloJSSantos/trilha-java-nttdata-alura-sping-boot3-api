package br.com.alura.med.voll.api.dominio.medico;

import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDadosCadastro(
       @NotBlank @Column(unique = true)
       String nome,
       @NotBlank @Pattern(regexp = "\\d{4,7}") @Column(unique = true)
       String crm,
       @NotBlank @Email @Column(unique = true)
       String email,
       @NotBlank
       String telefone,
       @NotNull
       Especialidade especialidade,
       @NotNull @Valid
       EnderecoDados endereco) {
}
