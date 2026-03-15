package org.example.dao;

import org.example.model.Product;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public int addProduct(Product product){

        String sql = "INSERT INTO products (product_name, category_id, supplier_id, price, stock) VALUES (?,?,?,?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getCategoryId());
            ps.setInt(3, product.getSupplierId());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public List<Product> getAllProducts(){

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setSupplierId(rs.getInt("supplier_id"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));

                products.add(p);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setProductName(rs.getString("product_name"));
                    p.setCategoryId(rs.getInt("category_id"));
                    p.setSupplierId(rs.getInt("supplier_id"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStock(rs.getInt("stock"));
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getLowStockProducts(int threshold) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE stock < ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, threshold);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setProductName(rs.getString("product_name"));
                    p.setCategoryId(rs.getInt("category_id"));
                    p.setSupplierId(rs.getInt("supplier_id"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStock(rs.getInt("stock"));
                    products.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET product_name = ?, category_id = ?, supplier_id = ?, price = ?, stock = ? WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getCategoryId());
            ps.setInt(3, product.getSupplierId());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setInt(6, product.getProductId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
