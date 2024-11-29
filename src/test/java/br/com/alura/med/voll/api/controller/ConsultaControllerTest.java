package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosDetalhamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaService;
import br.com.alura.med.voll.api.dominio.medico.Especialidade;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultaDadosAgendamento> consultaDadosAgendamentoJsonRequest;

    @Autowired
    private JacksonTester<ConsultaDadosDetalhamento> consultaDadosDetalhamentoJsonResponse;

    @MockBean
    private ConsultaService consulta;

    @Test
    @DisplayName("Deve retornar HTTP 400 quando dados forem inválidos")
    @WithMockUser
    void agendaConsultaCenario01() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar HTTP 200 quando dados forem válidos")
    @WithMockUser
    void agendaConsultaCenario02() throws Exception {
        var dataAgendamento = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.ORTOPEDIA;
        var jsonEnviado = consultaDadosAgendamentoJsonRequest.write(
                new ConsultaDadosAgendamento(3l, 6l, dataAgendamento, especialidade)).getJson();

        var dadosDetalhamento = new ConsultaDadosDetalhamento(null, 3l, 6l, dataAgendamento);
        var jsonEsperado = consultaDadosDetalhamentoJsonResponse.write(dadosDetalhamento).getJson();

        when(consulta.agendaConsulta(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEnviado)
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}