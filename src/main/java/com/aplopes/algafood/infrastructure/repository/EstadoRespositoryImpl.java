package com.aplopes.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.aplopes.algafood.domain.model.Estado;
import com.aplopes.algafood.domain.repository.EstadoRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class EstadoRespositoryImpl implements EstadoRepository {
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery(
            "from Estado", Estado.class
        ).getResultList();
    }

    @Override
    public Estado buscar(Long id) {

        Estado estado = manager.find(Estado.class, id);

        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return estado;
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long id) {

        Estado estado = buscar(id);
        manager.remove(estado);
    }
}
