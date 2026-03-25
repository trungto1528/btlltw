package com.btl.ltw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.btl.ltw.enums.Size;
import com.btl.ltw.model.ProductVariant;

@Repository
public class ProductVariantDAO extends BaseDAO {

    public List<ProductVariant> findAll() {
        List<ProductVariant> variants = new ArrayList<>();
        String sql = "SELECT * FROM product_variant";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                variants.add(mapResultSetToProductVariant(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return variants;
    }

    public Optional<ProductVariant> findById(Long id) {
        String sql = "SELECT * FROM product_variant WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProductVariant(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<ProductVariant> findByProductId(Long productId) {
        List<ProductVariant> variants = new ArrayList<>();
        String sql = "SELECT * FROM product_variant WHERE product_id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    variants.add(mapResultSetToProductVariant(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return variants;
    }

    public void save(ProductVariant variant) {
        String sql = "INSERT INTO product_variant (product_id, size, color, stock_quantity) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, variant.getProductId());
            pstmt.setString(2, variant.getSize().toString());
            pstmt.setString(3, variant.getColor());
            pstmt.setInt(4, variant.getStockQuantity());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ProductVariant variant) {
        String sql = "UPDATE product_variant SET product_id = ?, size = ?, color = ?, stock_quantity = ? WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, variant.getProductId());
            pstmt.setString(2, variant.getSize().toString());
            pstmt.setString(3, variant.getColor());
            pstmt.setInt(4, variant.getStockQuantity());
            pstmt.setLong(5, variant.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM product_variant WHERE id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ProductVariant mapResultSetToProductVariant(ResultSet rs) throws SQLException {
        ProductVariant variant = new ProductVariant();
        variant.setId(rs.getLong("id"));
        variant.setProductId(rs.getLong("product_id"));

        String sizeStr = rs.getString("size");
        if (sizeStr != null) {
            variant.setSize(Size.valueOf(sizeStr));
        }

        variant.setColor(rs.getString("color"));
        variant.setStockQuantity(rs.getInt("stock_quantity"));
        return variant;
    }
}
