package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.Permissao;

public interface PermissaoRepository {
    
    public abstract List<Permissao> listar();
    public abstract Permissao buscar(Long id);
    public abstract Permissao salvar(Permissao permissao);
    public abstract void remover(Permissao permissao);
}
