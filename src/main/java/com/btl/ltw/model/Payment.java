package com.btl.ltw.model;

import java.time.LocalDateTime;

import com.btl.ltw.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private Long orderId;
    private double amount;
    private PaymentMethod paymentMethod; // COD, BANK, MOMO
    private LocalDateTime paymentDate;
}