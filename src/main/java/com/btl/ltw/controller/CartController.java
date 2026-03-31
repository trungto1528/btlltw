package com.btl.ltw.controller;

import com.btl.ltw.model.Cart;
import com.btl.ltw.model.User;
import com.btl.ltw.services.CartItemService;
import com.btl.ltw.services.CartService;
import com.btl.ltw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("variantId") Long variantId,
            @RequestParam("quantity") Integer quantity,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        // 1. Kiểm tra đăng nhập
        if (principal == null) {
            return "redirect:/auth/login";
        }

        try {
            // 2. Lấy User hiện tại từ Principal
            User user = userService.findByUsername(principal.getName());

            // 3. Gọi Service để thực hiện logic nghiệp vụ (đã có @Transactional)
            cartService.addToCart(user, variantId, quantity);

            // 4. Gửi thông báo thành công sang trang tiếp theo (Flash Attributes)
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm sản phẩm vào giỏ hàng thành công!");

        } catch (Exception e) {
            // Nếu có lỗi (ví dụ: hết hàng), báo lỗi
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }

        // 5. Chuyển hướng người dùng (Redirect)
        // Bạn có thể redirect về trang giỏ hàng hoặc chính trang sản phẩm đó
        return "redirect:/products";
    }

    @GetMapping("/view")
    public String viewCart(Model model, Principal principal) {
        if (principal == null) return "redirect:/auth/login";

        User user = userService.findByUsername(principal.getName());
        // Lấy giỏ hàng của user
        Cart cart = cartService.getCartByUser(user);
        
        if (cart != null) {
            model.addAttribute("cartItems", cart.getCartItems());
            // Tính tổng tiền tạm tính
            double total = cart.getCartItems().stream()
                    .mapToDouble(item -> item.getVariant().getProduct().getPrice() * item.getQuantity())
                    .sum();
            model.addAttribute("totalPrice", total);
        } else {
            model.addAttribute("cartItems", new ArrayList<>());
            model.addAttribute("totalPrice", 0);
        }

        return "cart-page";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("itemId") Long itemId) {
        cartItemService.deleteById(itemId);
        return "redirect:/cart/view";
    }
}