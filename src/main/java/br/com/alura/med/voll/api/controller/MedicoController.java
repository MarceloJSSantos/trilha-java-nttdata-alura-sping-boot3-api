package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.medico.*;
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

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDadosCadastro dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDadosAposCadastroAtualizacao(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDadosListagem>> listarPaginado(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao){
//        return medicoRepository.findAll(paginacao).map(MedicoDadosListagem::new);
        var respotaPaginada = medicoRepository.findAllByAtivoTrue(paginacao).
                map(MedicoDadosListagem::new);
        return ResponseEntity.ok(respotaPaginada);
    }

    @GetMapping("/naopaginado")
    public ResponseEntity<List<MedicoDadosListagem>> listar(){
        var listagem = medicoRepository.findAll().stream().map(MedicoDadosListagem::new).toList();
        return ResponseEntity.ok(listagem);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoDadosParaAtualizacao dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizaDados(dados);
        return ResponseEntity.ok(new MedicoDadosAposCadastroAtualizacao(medico));
    }

    @DeleteMapping("/exclusaodefinitiva/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFormaLogica(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.exclusaoLodica();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity consultarMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDadosAposCadastroAtualizacao(medico));
    }

}
