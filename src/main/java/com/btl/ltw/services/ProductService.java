package com.btl.ltw.services;

import com.btl.ltw.dao.ProductDAO;
import com.btl.ltw.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(Long id) {
        return productDAO.findById(id).orElse(null);
    }

    public List<Product> searchProducts(String keyword) {
        return productDAO.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }

    public List<Product> getFeaturedProducts() {
        return productDAO.findTop4ByOrderByIdDesc();
    }
}
