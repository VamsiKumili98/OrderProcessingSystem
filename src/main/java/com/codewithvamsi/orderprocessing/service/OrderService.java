package com.codewithvamsi.orderprocessing.service;

import com.codewithvamsi.orderprocessing.downstream.InventoryClient;
import com.codewithvamsi.orderprocessing.dto.OrderDto;
import com.codewithvamsi.orderprocessing.dto.OrderItemDto;
import com.codewithvamsi.orderprocessing.exception.NotFoundException;
import com.codewithvamsi.orderprocessing.exception.ProductUnavailableException;
import com.codewithvamsi.orderprocessing.model.Order;
import com.codewithvamsi.orderprocessing.model.OrderItem;
import com.codewithvamsi.orderprocessing.model.response.Product;
import com.codewithvamsi.orderprocessing.repository.OrderRepository;
import com.codewithvamsi.orderprocessing.response.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private InventoryClient inventoryClient;

    @Transactional
    public OrderResponse createOrder(OrderDto orderDetails) {
        validateOrderDto(orderDetails);
        Order order = orderRepository.save(mapToOrder(orderDetails));
        return objectMapper.convertValue(order, OrderResponse.class);
    }

    public OrderResponse getOrder(Long id) throws NotFoundException {
        Order order = orderRepository.findById(id).orElse(null);
        if(order==null) throw new NotFoundException("Order not found");
        return mapToOrderResponse(order);
    }

    public List<OrderResponse> getOrders(){
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order-> mapToOrderResponse(order)).toList();
    }
    private void validateOrderDto(OrderDto orderDto) {
        orderDto.orderItemDtoList().forEach(orderItemDto -> {
            if(!checkProductAvailablitiy(orderItemDto)) {
                throw new ProductUnavailableException("Product "+orderItemDto.productId()+
                        "  is unavailable");
            }
        });
    }
    private boolean checkProductAvailablitiy(OrderItemDto orderItemDto){
        Product productResponseEntity = inventoryClient.getProduct(orderItemDto.productId());
        if(productResponseEntity==null || productResponseEntity.getAvailable()==null)
            throw new ProductUnavailableException(
                    "Inventory info unavailable for product " + orderItemDto.productId()
            );
        return productResponseEntity.getAvailable();
    }
    private OrderResponse mapToOrderResponse(Order order){
        return objectMapper.convertValue(order, OrderResponse.class);
    }
    private Order mapToOrder(OrderDto orderDto){
        Order entity = new Order();
        entity.setUserId(orderDto.userId());
        entity.setTotalAmount(orderDto.totalAmount());
        List<OrderItem> orderItemList = orderDto.orderItemDtoList().stream().map(itemDto -> {
            OrderItem orderItem = mapToOrderItem(itemDto);
            orderItem.setOrder(entity);
            return orderItem;
        }).toList();
        entity.setOrderItemList(orderItemList);
        return entity;
    }

    private OrderItem mapToOrderItem(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.productId());
        orderItem.setProductName(orderItemDto.productName());
        orderItem.setQuantity(orderItemDto.quantity());
        orderItem.setPrice(orderItemDto.price());
        return orderItem;
    }
}
