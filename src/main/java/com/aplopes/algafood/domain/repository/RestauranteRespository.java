package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.Restaurante;

public interface RestauranteRespository {
    
    public abstract List<Restaurante> listar();
    public abstract Restaurante buscar(Long id);
    public abstract Restaurante salvar(Restaurante restaurante);
    public abstract void remover(Long id);
}
