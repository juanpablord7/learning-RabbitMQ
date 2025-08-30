package com.miempresa.serviceorder.rabbit;

import com.miempresa.serviceorder.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Order order){
        rabbitTemplate.convertAndSend("orderQueue", order);
        System.out.println("Sent message: " + order);
    }
}
