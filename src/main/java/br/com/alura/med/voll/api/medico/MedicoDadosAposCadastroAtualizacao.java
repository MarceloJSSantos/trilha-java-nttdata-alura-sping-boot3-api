package br.com.alura.med.voll.api.medico;

import br.com.alura.med.voll.api.endereco.Endereco;

public record MedicoDadosAposCadastroAtualizacao(
        Long id,
        String nome,
        String crm,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {
    public MedicoDadosAposCadastroAtualizacao(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(),
                medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
