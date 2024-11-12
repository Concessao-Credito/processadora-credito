package com.challenge.processadora_credito.service.impl;

import com.challenge.processadora_credito.api.ProcessadoraApi;
import com.challenge.processadora_credito.config.CustomMapper;
import com.challenge.processadora_credito.domain.*;
import com.challenge.processadora_credito.exception.ApiExternaException;
import com.challenge.processadora_credito.producer.CaptacaoPublisher;
import com.challenge.processadora_credito.service.ProcessadoraCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessadoraCreditoServiceImpl implements ProcessadoraCreditoService {

    private final CaptacaoPublisher captacaoPublisher;
    private final ProcessadoraApi processadoraApi;
    private final CustomMapper customMapper;

    @Override
    public void process(MotorCreditoCompletoRequest message) throws ApiExternaException {
        try {
            Proposta proposta = processadoraApi.send(message);

            if (proposta == null) {
                throw new ApiExternaException("Não é possivel prosseguir");
            }

            Cliente cliente = customMapper.map(message, Cliente.class);

            ProcessadoraCreditoCompletoRequest request = ProcessadoraCreditoCompletoRequest
                    .builder()
                    .propostaRequest(proposta)
                    .statusEnum(StatusEnum.APROVADO)
                    .clienteRequest(cliente)
                    .build();

            captacaoPublisher.sendMessage(request);
        } catch (Exception e) {
            throw new ApiExternaException("Não é possivel prosseguir");
        }

    }
}
