package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
    
    public abstract List<Cozinha> listar();
    public abstract Cozinha buscar(Long id);
    public abstract Cozinha salvar(Cozinha cozinha);
    public abstract void remover(Long id);
}
