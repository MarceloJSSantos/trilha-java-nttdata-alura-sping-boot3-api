package br.com.alura.med.voll.api.paciente;

public record PacienteDadosListagem(Long id, String nome, String email, String cpf) {
    public PacienteDadosListagem(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
