package com.codewithvamsi.orderprocessing.controller;

import com.codewithvamsi.orderprocessing.dto.OrderDto;
import com.codewithvamsi.orderprocessing.exception.NotFoundException;
import com.codewithvamsi.orderprocessing.response.OrderResponse;
import com.codewithvamsi.orderprocessing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody OrderDto orderDetails){
        return orderService.createOrder(orderDetails);
    }

    @GetMapping("/orders")
    public List<OrderResponse> createOrder(){
        return orderService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id) throws NotFoundException {
        return orderService.getOrder(id);
    }
}
