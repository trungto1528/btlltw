package com.btl.ltw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCoupon {
    private Long id;
    private Long orderId;
    private Long couponId;
}
