package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.medico.MedicoDadosCadastro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody MedicoDadosCadastro dados){
        System.out.println(dados);
    }
}
