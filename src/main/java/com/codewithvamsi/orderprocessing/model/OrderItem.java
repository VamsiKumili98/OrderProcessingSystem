package com.codewithvamsi.orderprocessing.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    @JsonIgnore
    private Order order;
}
