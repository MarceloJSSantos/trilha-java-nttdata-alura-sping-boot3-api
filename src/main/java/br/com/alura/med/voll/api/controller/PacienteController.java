package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDadosCadastro dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDadosAposCadastroAtualizacao(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDadosListagem>> listar(@PageableDefault(size = 15, sort = {"nome"},
            direction = Sort.Direction.ASC) Pageable paginacao){
        var respostaPaginada = repository.findAllByAtivoTrue(paginacao).
                map(PacienteDadosListagem::new);
        return ResponseEntity.ok(respostaPaginada);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PacienteDadosParaAtualizacao dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
        return ResponseEntity.ok(new PacienteDadosAposCadastroAtualizacao(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFormaLogica(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.exclusaoLodica();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity consultarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDadosAposCadastroAtualizacao(paciente));
    }

}


