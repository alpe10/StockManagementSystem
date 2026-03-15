package org.example.view;

import org.example.controller.CategoryController;
import org.example.model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoryPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final JTextField nameField;
    private final JButton addBtn;
    private final JButton deleteBtn;

    private final CategoryController categoryController = new CategoryController();

    public CategoryPanel() {
        setLayout(new BorderLayout());

        // Table + model
        String[] columns = {"ID", "Category Name"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form + buttons
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inputPanel.add(new JLabel("Category Name:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);

        addBtn = new JButton("Add");
        deleteBtn = new JButton("Delete Selected");

        inputPanel.add(addBtn);
        inputPanel.add(deleteBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Listeners
        addBtn.addActionListener(e -> addCategory());
        deleteBtn.addActionListener(e -> deleteCategory());

        // Initial data
        loadCategories();
    }

    private void addCategory() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a category name.");
            return;
        }

        Category category = new Category();
        category.setCategoryName(name);
        categoryController.addCategory(category);

        nameField.setText("");
        loadCategories();
    }

    private void deleteCategory() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz satırı seçin!");
            return;
        }

        Object idValue = model.getValueAt(row, 0);
        int id = Integer.parseInt(idValue.toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Seçili kategoriyi silmek istediğinize emin misiniz?",
                "Silme Onayı",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            categoryController.deleteCategory(id);
            JOptionPane.showMessageDialog(this, "Başarıyla silindi!");
            loadCategories();
        }
    }

    private void loadCategories() {
        model.setRowCount(0);
        List<Category> categories = categoryController.getAllCategories();
        for (Category category : categories) {
            model.addRow(new Object[]{category.getCategoryId(), category.getCategoryName()});
        }
    }
}
