package com.btl.ltw.controller;

import com.btl.ltw.model.Product;
import com.btl.ltw.model.ProductVariant;
import com.btl.ltw.services.ProductService;
import com.btl.ltw.services.VariantService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private VariantService variantService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        logger.info("=== GET /products ===");
        try {
            List<Product> products = productService.getAllProducts();
            logger.info("Products found: " + (products != null ? products.size() : 0));
            model.addAttribute("products", products);
            return "products";
        } catch (Exception e) {
            logger.error("Error fetching products", e);
            throw e;
        }
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        List<ProductVariant> variants = variantService.findByProductId(id);

        List<Map<String, Object>> variantList = variants.stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("color", v.getColor());
            map.put("size", v.getSize().toString()); // Lấy tên size
            map.put("stockQuantity", v.getStockQuantity());
            return map;
        }).collect(Collectors.toList());

        // Trích xuất danh sách Màu không trùng lặp
        Set<String> colors = variants.stream()
                .map(ProductVariant::getColor)
                .collect(Collectors.toSet());

        // Trích xuất danh sách Size không trùng lặp
        Set<String> sizes = variants.stream()
                .map(v -> v.getSize().name()) // Nếu size là Enum
                .collect(Collectors.toSet());

        model.addAttribute("product", product);
        model.addAttribute("variants", variantList); // Dùng cho JavaScript check stock
        model.addAttribute("availableColors", colors); // Dùng cho th:each Màu sắc
        model.addAttribute("availableSizes", sizes);   // Dùng cho th:each Kích thước
        return "product-detail";
    }

    @GetMapping("/products/category/{categoryId}")
    public String getProductsByCategory(@PathVariable Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "products";
    }
}
