package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.Estado;

public interface EstadoRepository {
    
    public abstract List<Estado> listar();
    public abstract Estado buscar(Long id);
    public abstract Estado salvar(Estado estado);
    public abstract void remover(Long id);
}
