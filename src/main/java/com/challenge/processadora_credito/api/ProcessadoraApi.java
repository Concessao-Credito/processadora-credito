package com.challenge.processadora_credito.api;

import com.challenge.processadora_credito.domain.MotorCreditoCompletoRequest;
import com.challenge.processadora_credito.domain.Proposta;
import org.springframework.web.client.RestClientException;

public interface ProcessadoraApi {
    Proposta send(MotorCreditoCompletoRequest message) throws RestClientException;
}
