package com.challenge.processadora_credito.listerner;

import com.challenge.processadora_credito.config.RabbitMQConfig;
import com.challenge.processadora_credito.domain.MotorCreditoCompletoRequest;
import com.challenge.processadora_credito.service.ProcessadoraCreditoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MotorCreditoCompletoListerner {

    private final ProcessadoraCreditoService service;

    @RabbitListener(queues = RabbitMQConfig.MOTOR_CREDITO_P2_CONCLUIDO_QUEUE)
    public void receiveMessage(MotorCreditoCompletoRequest message) {
        log.info("Iniciando o processamento dos dados");
        service.process(message);
    }

}
