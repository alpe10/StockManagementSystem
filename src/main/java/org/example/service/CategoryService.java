package org.example.service;

import org.example.dao.CategoryDAO;
import org.example.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAO();

    public void addCategory(Category category){
        categoryDAO.addCategory(category);
    }

    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }
}