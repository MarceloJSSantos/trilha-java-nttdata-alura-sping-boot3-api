package br.com.alura.med.voll.api.medico;

public record MedicoDadosListagem(Long id, String nome,
                                  String crm,
                                  String email,
                                  Especialidade especialidade) {

    public MedicoDadosListagem(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
