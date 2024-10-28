package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.dominio.usuario.Usuario;
import br.com.alura.med.voll.api.infra.seguranca.DadosTokenJWT;
import br.com.alura.med.voll.api.infra.seguranca.TokenService;
import br.com.alura.med.voll.api.usuario.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuaLogin (@RequestBody @Valid DadosAutenticacao dados){
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var usuarioAutenticado =  manager.authenticate(tokenAutenticacao);

        var tokenJWT = tokenService.geraToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
