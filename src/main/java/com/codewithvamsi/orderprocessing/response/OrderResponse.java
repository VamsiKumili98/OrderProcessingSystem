package com.codewithvamsi.orderprocessing.response;

import com.codewithvamsi.orderprocessing.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long totalAmount;
    private OrderStatus orderStatus;
    private String createdAt;
    private String updatedAt;

    private List<OrderItemResponse> orderItemList;
}
