package com.demo.edarabbitmqorderservice.controller;

import com.demo.edarabbitmqorderservice.model.Order;
import com.demo.edarabbitmqorderservice.model.OrderEvent;
import com.demo.edarabbitmqorderservice.rabbitmq.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderEventProducer orderEventProducer;

    public OrderController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("successfully created");
        orderEvent.setOrder(order);
        orderEventProducer.produceOrderEvent(orderEvent);
        return ResponseEntity.ok("successfully created");
    }

}
