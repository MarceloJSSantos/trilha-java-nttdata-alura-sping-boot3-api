package br.com.alura.med.voll.api.medico;

import br.com.alura.med.voll.api.endereco.Endereco;

public record MedicoDadosCadastro(String nome,
                                  String crm,
                                  String email,
                                  Especialidade especialidade,
                                  Endereco endereco) {
}
