package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaService;
import br.com.alura.med.voll.api.dominio.consulta.DadosCancelamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendaConsulta(@RequestBody @Valid ConsultaDadosAgendamento dados){
        var consultaAgendada = consulta.agendaConsulta(dados);
        return ResponseEntity.ok(consultaAgendada);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consulta.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
