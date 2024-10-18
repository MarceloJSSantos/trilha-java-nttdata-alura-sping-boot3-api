package br.com.alura.med.voll.api.medico;

public record MedicoDadosListagem(String nome,
                                  String crm,
                                  String email,
                                  Especialidade especialidade) {

    public MedicoDadosListagem(Medico medico) {
        this(medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
