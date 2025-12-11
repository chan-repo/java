package com.materials.dao;

import com.materials.config.DatabaseConfig;
import com.materials.model.StockHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockHistoryDAO {

    public List<StockHistory> findAll() {
        List<StockHistory> histories = new ArrayList<>();
        String sql = """
            SELECT sh.*, m.code as material_code, m.name as material_name
            FROM stock_history sh
            JOIN materials m ON sh.material_id = m.id
            ORDER BY sh.created_at DESC
            LIMIT 100
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                histories.add(mapResultSetToHistory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    public List<StockHistory> findByMaterialId(Long materialId) {
        List<StockHistory> histories = new ArrayList<>();
        String sql = """
            SELECT sh.*, m.code as material_code, m.name as material_name
            FROM stock_history sh
            JOIN materials m ON sh.material_id = m.id
            WHERE sh.material_id = ?
            ORDER BY sh.created_at DESC
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, materialId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                histories.add(mapResultSetToHistory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    public StockHistory save(StockHistory history) {
        String sql = """
            INSERT INTO stock_history (material_id, type, quantity, stock_before, 
                                      stock_after, note, performed_by)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, history.getMaterialId());
            pstmt.setString(2, history.getType().name());
            pstmt.setInt(3, history.getQuantity());
            pstmt.setInt(4, history.getStockBefore());
            pstmt.setInt(5, history.getStockAfter());
            pstmt.setString(6, history.getNote());
            pstmt.setString(7, history.getPerformedBy());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                history.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    private StockHistory mapResultSetToHistory(ResultSet rs) throws SQLException {
        return StockHistory.builder()
                .id(rs.getLong("id"))
                .materialId(rs.getLong("material_id"))
                .materialCode(rs.getString("material_code"))
                .materialName(rs.getString("material_name"))
                .type(StockHistory.TransactionType.valueOf(rs.getString("type")))
                .quantity(rs.getInt("quantity"))
                .stockBefore(rs.getInt("stock_before"))
                .stockAfter(rs.getInt("stock_after"))
                .note(rs.getString("note"))
                .performedBy(rs.getString("performed_by"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
