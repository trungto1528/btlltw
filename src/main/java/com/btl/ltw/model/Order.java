package com.btl.ltw.model;

import java.time.LocalDateTime;

import com.btl.ltw.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private double totalAmount;
    private String shippingAddress;
    private OrderStatus status; // PENDING, CONFIRMED, SHIPPING, COMPLETED, CANCELLED
    private LocalDateTime createdAt;
}