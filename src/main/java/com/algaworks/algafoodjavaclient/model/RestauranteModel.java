package com.algaworks.algafoodjavaclient.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteModel {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}
