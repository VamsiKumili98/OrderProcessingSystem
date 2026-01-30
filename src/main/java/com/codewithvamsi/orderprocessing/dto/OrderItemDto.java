package com.codewithvamsi.orderprocessing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemDto(@JsonProperty("product_id") Long productId,
                           @JsonProperty("product_name") String productName,
                           Integer quantity,
                           Integer price) {

}
