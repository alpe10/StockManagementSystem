package org.example.dao;

import org.example.model.Order;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO {

    public void addOrder(Order order){

        String sql = "INSERT INTO orders (product_id, quantity) VALUES (?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, order.getProductId());
            ps.setInt(2, order.getQuantity());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}