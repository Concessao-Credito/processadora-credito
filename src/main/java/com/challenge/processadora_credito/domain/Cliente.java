package com.challenge.processadora_credito.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String rg;
    private String cep;
    private String endereco;
    private String profissao;
    private BigDecimal renda;
}
