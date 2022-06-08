package com.aplopes.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aplopes.algafood.domain.exception.EntidadeEmUsoException;
import com.aplopes.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.aplopes.algafood.domain.model.Estado;
import com.aplopes.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(Long id) {

        try {
            return estadoRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de estado com código %d",
                id
            ));
        }
    }

    public Estado adicionar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public Estado atualizar(Long id, Estado estado) {

        try {
            estadoRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de estado com código %d",
                id
            ));
        }

        estado.setId(id);
        return estadoRepository.salvar(estado);
    }

    public void deletar(Long id) {

        try {
            estadoRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de estado com código %d",
                id
            ));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                "Estado de código %d não pode ser removido, pois está em uso",
                id
            ));
        }
    }
}
