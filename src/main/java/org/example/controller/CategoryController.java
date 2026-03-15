package org.example.controller;

import org.example.dao.CategoryDAO;
import org.example.model.Category;
import java.util.List;

public class CategoryController {

    // 1. Önce DAO nesnesini burada tanımlaman gerekir (Hatanın ana sebebi bu)
    private CategoryDAO categoryDao = new CategoryDAO();

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    // 2. Silme metodunu buraya ekliyoruz
    public void deleteCategory(int id) {
        // DAO'daki isimle aynı olmalı: deleteCategory
        categoryDao.deleteCategory(id);
    }
}