package br.com.alura.med.voll.api.infra.excecao;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class TratadorErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity trataErro400(MethodArgumentNotValidException e){
        var listaErros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(listaErros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity trataErro400(HttpMessageNotReadableException e){
        /* TODO: Melhorar*/
        var mensagem = e.getMessage();
        return ResponseEntity.badRequest().body(mensagem);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity trataErro400(NoResourceFoundException e){
        /* TODO: Melhorar*/
        var mensagem = e.getMessage();
        return ResponseEntity.badRequest().body(mensagem);
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
