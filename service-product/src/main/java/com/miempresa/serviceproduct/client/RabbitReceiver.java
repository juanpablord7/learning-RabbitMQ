package com.miempresa.serviceproduct.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    @RabbitListener(queues = "orderQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}