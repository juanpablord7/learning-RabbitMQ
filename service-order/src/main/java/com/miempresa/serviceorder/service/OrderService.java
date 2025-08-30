package com.miempresa.serviceorder.service;

import com.miempresa.serviceorder.dto.OrderRequest;
import com.miempresa.serviceorder.model.Order;
import com.miempresa.serviceorder.rabbit.RabbitSender;
import com.miempresa.serviceorder.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitSender rabbitSender;

    public List<Order> getAllOrder(){
        rabbitSender.sendMessage("Hello second microservice");

        return orderRepository.findAll();
    }

    public Order createOrder(@Valid OrderRequest request){
        Order newOrder = Order.builder()
                .productId(request.getProductId())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        Order order = orderRepository.save(newOrder);

        return order;
    }
}
