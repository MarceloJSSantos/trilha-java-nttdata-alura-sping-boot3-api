package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.paciente.PacienteDadosCadastro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody PacienteDadosCadastro dados) {
        System.out.println("dados recebido: " + dados);
    }

}


