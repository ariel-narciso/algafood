package com.aplopes.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aplopes.algafood.domain.exception.EntidadeEmUsoException;
import com.aplopes.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.aplopes.algafood.domain.model.Cozinha;
import com.aplopes.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(Long id) {

        try {
            return cozinhaRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cozinha com código %d",
                id
            ));
        }
    }

    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {

        try {
            cozinhaRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cozinha com código %d",
                id
            ));
        }

        cozinha.setId(id);
        return cozinhaRepository.salvar(cozinha);
    }

    public void remover(Long id) {

        try {
            cozinhaRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cozinha com código %d",
                id
            ));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                "Cozinha de código %d não pode ser removida, pois está em uso",
                id
            ));
        }
    }
}
