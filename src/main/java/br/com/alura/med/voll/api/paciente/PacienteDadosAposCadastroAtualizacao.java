package br.com.alura.med.voll.api.paciente;

import br.com.alura.med.voll.api.endereco.Endereco;

public record PacienteDadosAposCadastroAtualizacao(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco) {
    public PacienteDadosAposCadastroAtualizacao(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(),
                paciente.getTelefone(), paciente.getEndereco());
    }
}
