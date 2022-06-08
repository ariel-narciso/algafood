package com.aplopes.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aplopes.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.aplopes.algafood.domain.model.Restaurante;
import com.aplopes.algafood.domain.repository.CozinhaRepository;
import com.aplopes.algafood.domain.repository.RestauranteRespository;

@Service
public class CadastroRestauranteService {
    
    @Autowired
    private RestauranteRespository restauranteRespository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRespository.listar();
    }

    public Restaurante buscar(Long id) {

        try {
            return restauranteRespository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de restaurante com código %d",
                id
            ));
        }
    }

    public Restaurante adicionar(Restaurante restaurante) {

        Long id = restaurante.getCozinha().getId();

        try {
            cozinhaRepository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cozinha com código %d",
                id
            ));
        }

        return restauranteRespository.salvar(restaurante);
    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {

        try {
            restauranteRespository.buscar(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de restaurante com código %d",
                id
            ));
        }

        Long cozinhaId = restaurante.getCozinha().getId();

        try {
            cozinhaRepository.buscar(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de cozinha com código %d",
                cozinhaId
            ));
        }

        restaurante.setId(id);
        return restauranteRespository.salvar(restaurante);
    }

    public void deletar(Long id) {

        try {
            restauranteRespository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Não existe um cadastro de restaurante com código %d",
                id
            ));
        }
    }
}
