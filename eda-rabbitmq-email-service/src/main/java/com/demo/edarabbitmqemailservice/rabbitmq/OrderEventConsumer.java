package com.demo.edarabbitmqemailservice.rabbitmq;

import com.demo.edarabbitmqemailservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderEventConsumer.class);

    @RabbitListener(
            queues = "${rabbitmq.queue.email.name}"
    )
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received: %s", orderEvent.toString()));
    }

}
