package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosAgendamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaDadosDetalhamento;
import br.com.alura.med.voll.api.dominio.consulta.ConsultaService;
import br.com.alura.med.voll.api.dominio.consulta.DadosCancelamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendaConsulta(@RequestBody @Valid ConsultaDadosAgendamento dados){
        consulta.agendaConsulta(dados);
        return ResponseEntity.ok(new ConsultaDadosDetalhamento(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consulta.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
