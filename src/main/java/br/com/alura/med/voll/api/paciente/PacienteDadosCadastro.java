package br.com.alura.med.voll.api.paciente;

import br.com.alura.med.voll.api.endereco.EnderecoDados;

public record PacienteDadosCadastro(
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDados endereco
) {
}
