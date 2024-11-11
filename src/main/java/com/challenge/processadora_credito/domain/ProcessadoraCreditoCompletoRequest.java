package com.challenge.processadora_credito.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessadoraCreditoCompletoRequest {
    private Cliente clienteRequest;
    private Proposta propostaRequest;
    private StatusEnum statusEnum;
}
