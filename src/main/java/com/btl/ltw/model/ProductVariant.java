package com.btl.ltw.model;

import com.btl.ltw.enums.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    private Long id;
    private Size size; // S, M, L, XL
    private String color;
    private int stockQuantity;
    private Long productId;
}