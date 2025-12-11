package com.materials.dao;

import com.materials.config.DatabaseConfig;
import com.materials.model.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialDAO {

    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM materials ORDER BY created_at DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                materials.add(mapResultSetToMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    public List<Material> findActive() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE active = TRUE ORDER BY name";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                materials.add(mapResultSetToMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    public List<Material> findLowStock() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE current_stock <= min_stock AND active = TRUE";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                materials.add(mapResultSetToMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    public Optional<Material> findById(Long id) {
        String sql = "SELECT * FROM materials WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Material> findByCode(String code) {
        String sql = "SELECT * FROM materials WHERE code = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Material save(Material material) {
        if (material.getId() == null) {
            return insert(material);
        } else {
            return update(material);
        }
    }

    private Material insert(Material material) {
        String sql = """
            INSERT INTO materials (code, name, category, unit, unit_price, 
                                 current_stock, min_stock, location, description, active)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatement(pstmt, material);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                material.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    private Material update(Material material) {
        String sql = """
            UPDATE materials SET code = ?, name = ?, category = ?, unit = ?, 
                               unit_price = ?, current_stock = ?, min_stock = ?, 
                               location = ?, description = ?, active = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatement(pstmt, material);
            pstmt.setLong(11, material.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    public void updateStock(Long id, int newStock) {
        String sql = "UPDATE materials SET current_stock = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newStock);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "UPDATE materials SET active = FALSE WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findDistinctCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM materials WHERE category IS NOT NULL ORDER BY category";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private void setPreparedStatement(PreparedStatement pstmt, Material material) throws SQLException {
        pstmt.setString(1, material.getCode());
        pstmt.setString(2, material.getName());
        pstmt.setString(3, material.getCategory());
        pstmt.setString(4, material.getUnit());
        pstmt.setBigDecimal(5, material.getUnitPrice());
        pstmt.setInt(6, material.getCurrentStock());
        pstmt.setInt(7, material.getMinStock());
        pstmt.setString(8, material.getLocation());
        pstmt.setString(9, material.getDescription());
        pstmt.setBoolean(10, material.getActive());
    }

    private Material mapResultSetToMaterial(ResultSet rs) throws SQLException {
        return Material.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .category(rs.getString("category"))
                .unit(rs.getString("unit"))
                .unitPrice(rs.getBigDecimal("unit_price"))
                .currentStock(rs.getInt("current_stock"))
                .minStock(rs.getInt("min_stock"))
                .location(rs.getString("location"))
                .description(rs.getString("description"))
                .active(rs.getBoolean("active"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .build();
    }
}
