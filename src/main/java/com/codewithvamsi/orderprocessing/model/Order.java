package com.codewithvamsi.orderprocessing.model;

import com.codewithvamsi.orderprocessing.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @PrePersist
    public void createTimestamp(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
        this.orderStatus = OrderStatus.CREATED;
    }
    @PreUpdate
    public void updateTimeStamp(){
        this.updatedAt = LocalDateTime.now();
    }
}
