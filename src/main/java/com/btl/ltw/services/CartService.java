package com.btl.ltw.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btl.ltw.model.Cart;
import com.btl.ltw.model.CartItem;
import com.btl.ltw.model.ProductVariant;
import com.btl.ltw.model.User;
import com.btl.ltw.repository.CartItemRepository;
import com.btl.ltw.repository.CartRepository;
import com.btl.ltw.repository.ProductVariantRepository;



@Service
public class CartService {
    @Autowired private CartRepository cartRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private ProductVariantRepository variantRepository;

    @Transactional
    public void addToCart(User user, Long variantId, Integer quantity) {
        // 1. Tìm hoặc tạo Cart cho User
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // 2. Kiểm tra Variant có tồn tại không
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        // 3. Kiểm tra xem sản phẩm này đã có trong giỏ chưa
        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndVariantId(cart.getId(), variantId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setVariant(variant);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }


    public Cart getCartByUser(User user){
        return cartRepository.findByUserId(user.getId()).orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }
}
