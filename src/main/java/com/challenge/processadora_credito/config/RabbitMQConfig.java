package com.challenge.processadora_credito.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String MOTOR_CREDITO_P2_CONCLUIDO_QUEUE = "motor_credito_p2_concluido";
    public static final String CAPTACAO_QUEUE = "salvar_captacao";
    public static final String CAPTACAO_ROUTING_KEY = "captacao.routing.key";
    public static final String APP_EXCHANGE = "appExchange";

    @Bean
    public Queue motorCreditoP2ConcluidoQueue() {
        return new Queue(MOTOR_CREDITO_P2_CONCLUIDO_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue captacaoQueue() {
        return new Queue(CAPTACAO_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(APP_EXCHANGE);
    }

    @Bean
    public Binding captacaoQueueBinding(Queue captacaoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(captacaoQueue).to(exchange).with(CAPTACAO_ROUTING_KEY);
    }

}
