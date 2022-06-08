package com.aplopes.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.aplopes.algafood.domain.model.Restaurante;
import com.aplopes.algafood.domain.repository.RestauranteRespository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RestauranteRepositoryImpl implements RestauranteRespository {
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar() {
        return manager.createQuery(
            "from Restaurante", Restaurante.class
        ).getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {

        Restaurante restaurante = manager.find(Restaurante.class, id);

        if (restaurante == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return restaurante;
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Long id) {

        Restaurante restaurante = buscar(id);
        manager.remove(restaurante);
    }
}
