package com.aplopes.algafood.domain.repository;

import java.util.List;

import com.aplopes.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
    
    public abstract List<FormaPagamento> listar();
    public abstract FormaPagamento buscar(Long id);
    public abstract FormaPagamento salvar(FormaPagamento formaPagamento);
    public abstract void remover(FormaPagamento formaPagamento);
}
