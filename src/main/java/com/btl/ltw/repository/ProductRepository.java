package com.btl.ltw.repository;

import com.btl.ltw.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findTop4ByOrderByIdDesc();
}
