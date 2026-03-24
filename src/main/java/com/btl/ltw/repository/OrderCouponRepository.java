package com.btl.ltw.repository;

import com.btl.ltw.model.OrderCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCouponRepository extends JpaRepository<OrderCoupon, Long> {
    List<OrderCoupon> findByOrderId(Long orderId);
}
