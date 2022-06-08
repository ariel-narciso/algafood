package com.aplopes.algafood.jpa;

import java.math.BigDecimal;
import java.util.List;

import com.aplopes.algafood.AlgafoodApplication;
import com.aplopes.algafood.domain.model.Restaurante;
import com.aplopes.algafood.domain.repository.CidadeRepository;
import com.aplopes.algafood.domain.repository.EstadoRepository;
import com.aplopes.algafood.domain.repository.FormaPagamentoRepository;
import com.aplopes.algafood.domain.repository.PermissaoRepository;
// import com.aplopes.algafood.domain.model.Cozinha;
// import com.aplopes.algafood.domain.repository.CozinhaRepository;
import com.aplopes.algafood.domain.repository.RestauranteRespository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class Consulta {

    public static void main(String[] args) {
        
        ApplicationContext context = new SpringApplicationBuilder(
            AlgafoodApplication.class
        ).web(WebApplicationType.NONE).run(args);

        EstadoRepository estados = context.getBean(EstadoRepository.class);
        estados.listar().forEach(System.out::println);

        CidadeRepository cidades = context.getBean(CidadeRepository.class);
        cidades.listar().forEach(System.out::println);

        PermissaoRepository permissoes = context.getBean(PermissaoRepository.class);
        permissoes.listar().forEach(System.out::println);

        FormaPagamentoRepository formaPagamentos = context.getBean(FormaPagamentoRepository.class);
        formaPagamentos.listar().forEach(System.out::println);
    }
}
