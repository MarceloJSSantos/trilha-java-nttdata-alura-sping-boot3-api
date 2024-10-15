package br.com.alura.med.voll.api.endereco;

public record Endereco(String logradouro,
                       String bairro,
                       String cep,
                       String cidade,
                       String uf,
                       String numero,
                       String complemento) {
}