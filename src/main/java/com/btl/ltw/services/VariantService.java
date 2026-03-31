package com.btl.ltw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.ltw.model.ProductVariant;
import com.btl.ltw.repository.ProductVariantRepository;

@Service
public class VariantService {
    @Autowired
    private ProductVariantRepository variantRepository;

    public List<ProductVariant> findByProductId(Long productId){
        return variantRepository.findByProductId(productId);
    }
    
}
