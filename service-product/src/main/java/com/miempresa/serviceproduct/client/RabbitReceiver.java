package com.miempresa.serviceproduct.client;

import com.miempresa.serviceproduct.dto.ProductPatchRequest;
import com.miempresa.serviceproduct.dto.client.Order;
import com.miempresa.serviceproduct.model.Product;
import com.miempresa.serviceproduct.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "orderQueue")
    public void receiveMessage(Order order) {
        System.out.println("Received message: " + order);

        Product actualProduct = productService.findProductById(order.getProductId());

        ProductPatchRequest productUpdate = ProductPatchRequest.builder()
                .stock(actualProduct.getStock() - order.getQuantity())
                .build();

        productService.updateProduct(actualProduct.getId(), productUpdate);

    }
}