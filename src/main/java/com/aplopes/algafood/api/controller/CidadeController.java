package com.aplopes.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplopes.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.aplopes.algafood.domain.model.Cidade;
import com.aplopes.algafood.domain.service.cadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    
    @Autowired
    private cadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cadastroCidade.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        
        try {
            return ResponseEntity.ok(cadastroCidade.buscar(id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                e.getMessage()
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                cadastroCidade.adicionar(cidade)
            );
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {

        try {
            return ResponseEntity.ok(
                cadastroCidade.atualizar(id, cidade)
            );
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(
                e.getMessage()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        try {
            cadastroCidade.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                e.getMessage()
            );
        }
    }
}
