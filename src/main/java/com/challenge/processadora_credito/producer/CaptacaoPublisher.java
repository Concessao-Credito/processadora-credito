package com.challenge.processadora_credito.producer;

import com.challenge.processadora_credito.domain.ProcessadoraCreditoCompletoRequest;

public interface CaptacaoPublisher {
    void sendMessage(ProcessadoraCreditoCompletoRequest message);
}
