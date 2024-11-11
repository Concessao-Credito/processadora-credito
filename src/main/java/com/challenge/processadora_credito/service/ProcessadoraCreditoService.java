package com.challenge.processadora_credito.service;

import com.challenge.processadora_credito.domain.MotorCreditoCompletoRequest;

public interface ProcessadoraCreditoService {
    void process(MotorCreditoCompletoRequest message);
}
