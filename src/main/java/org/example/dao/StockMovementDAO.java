package org.example.dao;

import org.example.model.StockMovement;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class StockMovementDAO {

    public void addMovement(StockMovement movement) {
        String sql = "INSERT INTO stock_movements (product_id, type, quantity, date) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movement.getProductId());
            ps.setString(2, movement.getType());
            ps.setInt(3, movement.getQuantity());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(movement.getDate()));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<StockMovement> getAllMovements() {
        List<StockMovement> list = new ArrayList<>();
        String sql = "SELECT * FROM stock_movements ORDER BY date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StockMovement m = new StockMovement();
                m.setId(rs.getInt("id"));
                m.setProductId(rs.getInt("product_id"));
                m.setType(rs.getString("type"));
                m.setQuantity(rs.getInt("quantity"));
                LocalDateTime dt = rs.getTimestamp("date").toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                m.setDate(dt);
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
