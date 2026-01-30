package com.codewithvamsi.orderprocessing.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer price;
}
