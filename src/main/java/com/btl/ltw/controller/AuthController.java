package com.btl.ltw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btl.ltw.model.User;
import com.btl.ltw.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSucess(){

        return "home";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        try {
            userService.register(user);
            // Nếu thành công, về login kèm tham số success
            return "redirect:/auth/login?success";
        } catch (RuntimeException e) {
            // Nếu có lỗi (Trùng user, trùng email...), gửi thông báo lỗi quay lại trang
            // register
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user); // Giữ lại dữ liệu đã nhập
            return "register";
        }
    }
}