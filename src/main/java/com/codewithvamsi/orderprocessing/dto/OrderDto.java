package com.codewithvamsi.orderprocessing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record OrderDto(@JsonProperty("user_id") Long userId,
                       @JsonProperty("total_amount") Long totalAmount,
        @JsonProperty("order_items") List<OrderItemDto> orderItemDtoList) {
}
