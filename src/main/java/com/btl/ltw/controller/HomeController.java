package com.btl.ltw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.btl.ltw.services.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    // @GetMapping("/home")
    // public String home() {
    //     return "home";
    // }

    @GetMapping({ "/", "/home" })
    public String index(Model model) {
        // Gửi danh sách 8 sản phẩm nổi bật sang View
        model.addAttribute("featuredProducts", productService.getFeaturedProducts());
        return "home";
    }
}