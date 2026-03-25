package com.btl.ltw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.btl.ltw.model.OrderCoupon;

@Repository
public class OrderCouponDAO extends BaseDAO {

    public List<OrderCoupon> findAll() {
        List<OrderCoupon> orderCoupons = new ArrayList<>();
        String sql = "SELECT * FROM order_coupon";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                orderCoupons.add(mapResultSetToOrderCoupon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderCoupons;
    }

    public Optional<OrderCoupon> findById(Long id) {
        String sql = "SELECT * FROM order_coupon WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToOrderCoupon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<OrderCoupon> findByOrderId(Long orderId) {
        List<OrderCoupon> orderCoupons = new ArrayList<>();
        String sql = "SELECT * FROM order_coupon WHERE order_id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderCoupons.add(mapResultSetToOrderCoupon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderCoupons;
    }

    public void save(OrderCoupon orderCoupon) {
        String sql = "INSERT INTO order_coupon (order_id, coupon_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, orderCoupon.getOrderId());
            pstmt.setLong(2, orderCoupon.getCouponId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(OrderCoupon orderCoupon) {
        String sql = "UPDATE order_coupon SET order_id = ?, coupon_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, orderCoupon.getOrderId());
            pstmt.setLong(2, orderCoupon.getCouponId());
            pstmt.setLong(3, orderCoupon.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM order_coupon WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderCoupon mapResultSetToOrderCoupon(ResultSet rs) throws SQLException {
        OrderCoupon orderCoupon = new OrderCoupon();
        orderCoupon.setId(rs.getLong("id"));
        orderCoupon.setOrderId(rs.getLong("order_id"));
        orderCoupon.setCouponId(rs.getLong("coupon_id"));
        return orderCoupon;
    }
}
