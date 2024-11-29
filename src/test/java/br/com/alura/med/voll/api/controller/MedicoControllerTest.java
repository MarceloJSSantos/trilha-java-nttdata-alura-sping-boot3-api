package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.endereco.Endereco;
import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import br.com.alura.med.voll.api.dominio.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MedicoDadosCadastro> medicoDadosCadastroJsonRequest;

    @Autowired
    private JacksonTester<MedicoDadosAposCadastroAtualizacao> medicoDadosAposCadastroAtualizacaoJsonResponse;

    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deve devolver código http 400 quando dados estão inválidos")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código http 201 quando dados estão válidos")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new MedicoDadosCadastro(
                "Medico",
                "123456",
                "medico@voll.med",
                "61999999999",
                Especialidade.CARDIOLOGIA,
                enderecoDados());

        when(repository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoDadosCadastroJsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new MedicoDadosAposCadastroAtualizacao(
                null,
                dadosCadastro.nome(),
                dadosCadastro.crm(),
                dadosCadastro.email(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = medicoDadosAposCadastroAtualizacaoJsonResponse.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private EnderecoDados enderecoDados() {
        return new EnderecoDados(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}