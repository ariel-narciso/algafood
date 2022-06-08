package com.aplopes.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.aplopes.algafood.domain.model.Cozinha;
import com.aplopes.algafood.domain.repository.CozinhaRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public List<Cozinha> listar() {
        return manager.createQuery(
            "from Cozinha", Cozinha.class
        ).getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {

        Cozinha cozinha = manager.find(Cozinha.class, id);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return cozinha;
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {

        Cozinha cozinha = buscar(id);
        manager.remove(cozinha);
    }
}
