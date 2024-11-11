package com.challenge.processadora_credito.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Proposta {
    private Boolean ativa;
    private BigDecimal valorLiberado;
}
