package com.challenge.processadora_credito.api.impl;

import com.challenge.processadora_credito.api.ProcessadoraApi;
import com.challenge.processadora_credito.domain.MotorCreditoCompletoRequest;
import com.challenge.processadora_credito.domain.Proposta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessadoraApiImpl implements ProcessadoraApi {
    private final RabbitTemplate rabbitTemplate;

    @Override
    @Retryable(
            retryFor = RestClientException.class,
            backoff = @Backoff(delay = 2000, multiplier = 3))
    public Proposta send(MotorCreditoCompletoRequest message) throws RestClientException {
        Random random = new Random();
        if (!message.getCpf().equals("04332611020")) {
            log.info("Api externa funcionando normal seguimos com a proposta retornada");
            return Proposta
                    .builder()
                    .ativa(random.nextBoolean())
                    .valorLiberado(BigDecimal.valueOf(10000))
                    .build();
        }
        log.info("Api externa sem resposta");
        throw new RestClientException("");
    }

}
