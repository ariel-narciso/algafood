package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.Cidade;

public interface CidadeRepository {
    
    public abstract List<Cidade> listar();
    public abstract Cidade buscar(Long id);
    public abstract Cidade salvar(Cidade cidade);
    public abstract void remover(Long id);
}
