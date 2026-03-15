package org.example.dao;

import org.example.model.Category;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    // Kategori ekleme (Mevcut)
    public void addCategory(Category category){
        String sql = "INSERT INTO categories (category_name) VALUES (?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, category.getCategoryName());
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Kategorileri listeleme (Mevcut)
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    // --- YENİ EKLENEN: Kategori Silme ---
    public void deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            // Eğer kategori bir ürüne bağlıysa burada SQL hatası fırlatır.
        }
    }

    // --- YENİ EKLENEN: Kategori Güncelleme ---
    public void updateCategory(Category category) {
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}