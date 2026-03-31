package com.btl.ltw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btl.ltw.repository.CartItemRepository;

@Service
public class CartItemService {
    @Autowired private CartItemRepository cartItemRepository;

    @Transactional
    public void deleteById(Long id){
        cartItemRepository.deleteById(id);
    }
}
