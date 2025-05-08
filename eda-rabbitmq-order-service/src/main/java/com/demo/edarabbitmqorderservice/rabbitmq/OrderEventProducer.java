package com.demo.edarabbitmqorderservice.rabbitmq;

import com.demo.edarabbitmqorderservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventProducer.class);

    @Value("${rabbitmq.order.exchange.name}")
    private String orderExchangeName;

    @Value("${rabbitmq.order.routing.key}")
    private String orderRoutingKey;

    @Value("${rabbitmq.queue.email.routing.key}")
    private String emailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public OrderEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order Event produced : %s", orderEvent));

        //send message to stock microservice
        rabbitTemplate.convertAndSend(orderExchangeName, orderRoutingKey, orderEvent);

        //send message to email microservice
        rabbitTemplate.convertAndSend(orderExchangeName, emailRoutingKey, orderEvent);
    }

}
