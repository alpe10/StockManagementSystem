package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.example.controller.CategoryController;
import org.example.controller.SupplierController;
import org.example.controller.ProductController;

import org.example.model.Category;
import org.example.model.Supplier;
import org.example.model.Product;

public class AddProductFrame extends JFrame {

    private JTextField nameField;
    private JComboBox<Category> categoryBox;
    private JComboBox<Supplier> supplierBox;
    private JTextField priceField;
    private JTextField stockField;
    private JButton saveBtn;

    private CategoryController categoryController = new CategoryController();
    private SupplierController supplierController = new SupplierController();
    private ProductController productController = new ProductController();

    public AddProductFrame(){

        setTitle("Add Product");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        nameField = new JTextField();
        categoryBox = new JComboBox<>();
        supplierBox = new JComboBox<>();
        priceField = new JTextField();
        stockField = new JTextField();
        saveBtn = new JButton("Save");

        panel.add(new JLabel("Product Name"));
        panel.add(nameField);

        panel.add(new JLabel("Category"));
        panel.add(categoryBox);

        panel.add(new JLabel("Supplier"));
        panel.add(supplierBox);

        panel.add(new JLabel("Price"));
        panel.add(priceField);

        panel.add(new JLabel("Stock"));
        panel.add(stockField);

        panel.add(new JLabel());
        panel.add(saveBtn);

        add(panel);

        // DATABASE'DEN VERİ YÜKLE
        loadCategories();
        loadSuppliers();

        // BUTTON EVENT
        saveBtn.addActionListener(e -> addProduct());
    }

    // CATEGORY YÜKLE
    private void loadCategories(){

        List<Category> categories = categoryController.getAllCategories();

        for(Category c : categories){
            categoryBox.addItem(c);
        }
    }

    // SUPPLIER YÜKLE
    private void loadSuppliers(){

        List<Supplier> suppliers = supplierController.getAllSuppliers();

        for(Supplier s : suppliers){
            supplierBox.addItem(s);
        }
    }

    // PRODUCT EKLE
    private void addProduct() {

        try {

            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Category category = (Category) categoryBox.getSelectedItem();
            Supplier supplier = (Supplier) supplierBox.getSelectedItem();

            Product product = new Product();

            product.setProductName(name);
            product.setCategoryId(category.getCategoryId());
            product.setSupplierId(supplier.getSupplierId());
            product.setPrice(price);
            product.setStock(stock);

            int id = productController.addProduct(product);

            String msg = (id > 0) ? "Product Added! ID: " + id : "Product Added!";
            JOptionPane.showMessageDialog(this, msg);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding product");
            ex.printStackTrace();
        }

    }
}
