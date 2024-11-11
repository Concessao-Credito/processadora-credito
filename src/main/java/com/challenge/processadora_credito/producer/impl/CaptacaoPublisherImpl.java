package com.challenge.processadora_credito.producer.impl;

import com.challenge.processadora_credito.config.RabbitMQConfig;
import com.challenge.processadora_credito.domain.ProcessadoraCreditoCompletoRequest;
import com.challenge.processadora_credito.producer.CaptacaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptacaoPublisherImpl implements CaptacaoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(ProcessadoraCreditoCompletoRequest message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.APP_EXCHANGE, RabbitMQConfig.CAPTACAO_ROUTING_KEY, message);
    }
}
