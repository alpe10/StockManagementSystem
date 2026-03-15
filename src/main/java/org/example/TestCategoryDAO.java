package org.example;

import org.example.dao.CategoryDAO;
import org.example.model.Category;

public class TestCategoryDAO {

    public static void main(String[] args) {

        CategoryDAO dao = new CategoryDAO();

        Category category = new Category();
        category.setCategoryName("Electronics");

        dao.addCategory(category);

        System.out.println("Category added");

    }
}