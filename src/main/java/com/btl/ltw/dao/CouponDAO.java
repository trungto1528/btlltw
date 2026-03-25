package com.btl.ltw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.btl.ltw.enums.CouponStatus;
import com.btl.ltw.enums.DiscountType;
import com.btl.ltw.model.Coupon;

@Repository
public class CouponDAO extends BaseDAO {

    public List<Coupon> findAll() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM coupon";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                coupons.add(mapResultSetToCoupon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    public Optional<Coupon> findById(Long id) {
        String sql = "SELECT * FROM coupon WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCoupon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Coupon> findByCode(String code) {
        String sql = "SELECT * FROM coupon WHERE code = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCoupon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(Coupon coupon) {
        String sql = "INSERT INTO coupon (code, discount_type, discount_value, start_date, end_date, quantity, status) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coupon.getCode());
            pstmt.setString(2, coupon.getDiscountType().toString());
            pstmt.setDouble(3, coupon.getDiscountValue());
            pstmt.setObject(4, coupon.getStartDate());
            pstmt.setObject(5, coupon.getEndDate());
            pstmt.setInt(6, coupon.getQuantity());
            pstmt.setString(7, coupon.getStatus().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Coupon coupon) {
        String sql = "UPDATE coupon SET code = ?, discount_type = ?, discount_value = ?, " +
                "start_date = ?, end_date = ?, quantity = ?, status = ? WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coupon.getCode());
            pstmt.setString(2, coupon.getDiscountType().toString());
            pstmt.setDouble(3, coupon.getDiscountValue());
            pstmt.setObject(4, coupon.getStartDate());
            pstmt.setObject(5, coupon.getEndDate());
            pstmt.setInt(6, coupon.getQuantity());
            pstmt.setString(7, coupon.getStatus().toString());
            pstmt.setLong(8, coupon.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM coupon WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Coupon mapResultSetToCoupon(ResultSet rs) throws SQLException {
        Coupon coupon = new Coupon();
        coupon.setId(rs.getLong("id"));
        coupon.setCode(rs.getString("code"));

        String discountTypeStr = rs.getString("discount_type");
        if (discountTypeStr != null) {
            coupon.setDiscountType(DiscountType.valueOf(discountTypeStr));
        }

        coupon.setDiscountValue(rs.getDouble("discount_value"));
        coupon.setStartDate(rs.getObject("start_date", LocalDateTime.class));
        coupon.setEndDate(rs.getObject("end_date", LocalDateTime.class));
        coupon.setQuantity(rs.getInt("quantity"));

        String statusStr = rs.getString("status");
        if (statusStr != null) {
            coupon.setStatus(CouponStatus.valueOf(statusStr));
        }

        return coupon;
    }
}
