package com.btl.ltw.model;

import java.time.LocalDateTime;

import com.btl.ltw.enums.CouponStatus;
import com.btl.ltw.enums.DiscountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private Long id;
    private String code;
    private DiscountType discountType; // PERCENT, FIXED
    private double discountValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int quantity;
    private CouponStatus status; // ACTIVE, EXPIRED, INACTIVE
}
