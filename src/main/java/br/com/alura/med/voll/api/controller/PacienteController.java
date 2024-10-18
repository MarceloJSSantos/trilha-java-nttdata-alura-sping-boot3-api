package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.medico.MedicoDadosListagem;
import br.com.alura.med.voll.api.paciente.Paciente;
import br.com.alura.med.voll.api.paciente.PacienteDadosCadastro;
import br.com.alura.med.voll.api.paciente.PacienteDadosListagem;
import br.com.alura.med.voll.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDadosCadastro dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<PacienteDadosListagem> listar(@PageableDefault(size = 15, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao){
        return repository.findAll(paginacao).map(PacienteDadosListagem::new);
    }

}


