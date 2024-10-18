package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.medico.Medico;
import br.com.alura.med.voll.api.medico.MedicoDadosCadastro;
import br.com.alura.med.voll.api.medico.MedicoDadosListagem;
import br.com.alura.med.voll.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDadosCadastro dados){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<MedicoDadosListagem> listarPaginado(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao){
        return medicoRepository.findAll(paginacao).map(MedicoDadosListagem::new);
    }

    @GetMapping("/naopaginado")
    public List<MedicoDadosListagem> listar(){
        return medicoRepository.findAll().stream().map(MedicoDadosListagem::new).toList();
    }
}
