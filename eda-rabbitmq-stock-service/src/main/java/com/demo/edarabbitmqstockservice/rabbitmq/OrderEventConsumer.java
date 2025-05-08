package com.demo.edarabbitmqstockservice.rabbitmq;

import com.demo.edarabbitmqstockservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventConsumer.class);

    @RabbitListener(
            queues = "${rabbitmq.queue.order.name}"
    )
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Consuming order event: %s", orderEvent.toString()));
    }

}
