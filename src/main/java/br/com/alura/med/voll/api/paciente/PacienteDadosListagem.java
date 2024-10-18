package br.com.alura.med.voll.api.paciente;

public record PacienteDadosListagem(String nome, String email, String cpf) {
    public PacienteDadosListagem(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
