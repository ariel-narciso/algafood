package com.aplopes.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aplopes.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.aplopes.algafood.domain.model.Cidade;
import com.aplopes.algafood.domain.repository.CidadeRepository;
import com.aplopes.algafood.domain.repository.EstadoRepository;

@Service
public class cadastroCidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade buscar(Long id) {

        try {
            return cidadeRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cidade com código %d",
                id
            ));
        }
    }

    public Cidade adicionar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();

        try {
            estadoRepository.buscar(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de Estado com código %d",
                estadoId
            ));
        }

        return cidadeRepository.salvar(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {

        try {
            cidadeRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cidade com código %d",
                id
            ));
        }

        Long estadoId = cidade.getEstado().getId();

        try {
            estadoRepository.buscar(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de Estado com código %d",
                estadoId
            ));
        }

        cidade.setId(id);
        return cidadeRepository.salvar(cidade);
    }

    public void deletar(Long id) {

        try {
            cidadeRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cidade com código %d",
                id
            ));
        }
    }
}
