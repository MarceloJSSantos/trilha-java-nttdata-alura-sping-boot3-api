package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.medico.MedicoDadosAtualizacao;
import br.com.alura.med.voll.api.medico.MedicoDadosListagem;
import br.com.alura.med.voll.api.paciente.*;
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
        return repository.findAllByAtivoTrue(paginacao).map(PacienteDadosListagem::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteDadosAtualizacao dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirFormaLogica(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.exclusaoLodica();
    }

}


