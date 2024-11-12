package com.challenge.processadora_credito.listerner;

import com.challenge.processadora_credito.config.RabbitMQConfig;
import com.challenge.processadora_credito.domain.MotorCreditoCompletoRequest;
import com.challenge.processadora_credito.exception.ApiExternaException;
import com.challenge.processadora_credito.service.ProcessadoraCreditoService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MotorCreditoCompletoListerner {

    private final ProcessadoraCreditoService service;

    @SneakyThrows
    @RabbitListener(queues = RabbitMQConfig.MOTOR_CREDITO_P2_CONCLUIDO_QUEUE)
    public void receiveMessage(MotorCreditoCompletoRequest message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("Iniciando o processamento dos dados");
        try {
            service.process(message);
            channel.basicAck(tag, false);
        } catch (ApiExternaException apiExternaException) {
            channel.basicNack(tag, false, true);
        }
    }

}
