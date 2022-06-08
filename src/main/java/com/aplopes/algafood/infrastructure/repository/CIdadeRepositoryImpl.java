package com.aplopes.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.aplopes.algafood.domain.model.Cidade;
import com.aplopes.algafood.domain.repository.CidadeRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class CIdadeRepositoryImpl implements CidadeRepository {
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery(
            "from Cidade", Cidade.class
        ).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {

        Cidade cidade = manager.find(Cidade.class, id);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return cidade;
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        
        Cidade cidade = buscar(id);
        manager.remove(cidade);
    }
}
