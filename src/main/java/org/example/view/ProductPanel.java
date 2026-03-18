package org.example.view;

import org.example.controller.ProductController;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final JButton addProductBtn;
    private final JButton deleteBtn;
    private final JButton updateBtn; // Güncelleme için

    private final ProductController productController = new ProductController();

    public ProductPanel() {
        setLayout(new BorderLayout());

        // 1. Tablo Modelini Oluştur
        String[] columns = {"ID", "Name", "Category ID", "Supplier ID", "Price", "Stock"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tablonun direkt üzerine yazılmasını engelle
            }
        };

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // 2. Butonlar Paneli
        JPanel buttonPanel = new JPanel();
        addProductBtn = new JButton("Add Product");
        updateBtn = new JButton("Update Selected");
        deleteBtn = new JButton("Delete Selected");

        buttonPanel.add(addProductBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        add(scroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 3. Event Listenerlar (Olay Dinleyicileri)

        // Ürün Ekleme Penceresini Aç
        addProductBtn.addActionListener(e -> {
            AddProductFrame addFrame = new AddProductFrame();
            addFrame.setVisible(true);
            // Pencere kapandığında tabloyu yenilemek istersen listener eklenebilir
            addFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadProducts();
                }
            });
        });

        // Silme İşlemi
        deleteBtn.addActionListener(e -> deleteProduct());

        // Güncelleme İşlemi
        updateBtn.addActionListener(e -> updateProduct());

        // Verileri İlk Kez Yükle
        loadProducts();
    }

    // Ürünleri Veritabanından Çek ve Tabloya Bas
    public void loadProducts() {
        model.setRowCount(0); // Tabloyu temizle
        List<Product> products = productController.getAllProducts();

        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getProductId(),
                    p.getProductName(),
                    p.getCategoryId(),
                    p.getSupplierId(),
                    p.getPrice(),
                    p.getStock()
            });
        }
    }

    // Ürün Silme Metodu
    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int productId = (int) model.getValueAt(selectedRow, 0);
            productController.deleteProduct(productId);
            JOptionPane.showMessageDialog(this, "Product deleted");
            loadProducts();
        }
    }

    // Ürün Güncelleme Metodu
    private void updateProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to update!");
            return;
        }

        int productId = (int) model.getValueAt(selectedRow, 0);
        String currentName = model.getValueAt(selectedRow, 1).toString();
        String currentCategoryId = model.getValueAt(selectedRow, 2).toString();
        String currentSupplierId = model.getValueAt(selectedRow, 3).toString();
        String currentPrice = model.getValueAt(selectedRow, 4).toString();
        String currentStock = model.getValueAt(selectedRow, 5).toString();

        JTextField nameField = new JTextField(currentName);
        JTextField categoryField = new JTextField(currentCategoryId);
        JTextField supplierField = new JTextField(currentSupplierId);
        JTextField priceField = new JTextField(currentPrice);
        JTextField stockField = new JTextField(currentStock);

        JPanel panel = new JPanel(new GridLayout(5, 2, 6, 6));
        panel.add(new JLabel("Name"));
        panel.add(nameField);
        panel.add(new JLabel("Category ID"));
        panel.add(categoryField);
        panel.add(new JLabel("Supplier ID"));
        panel.add(supplierField);
        panel.add(new JLabel("Price"));
        panel.add(priceField);
        panel.add(new JLabel("Stock"));
        panel.add(stockField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Product product = new Product();
                product.setProductId(productId);
                product.setProductName(nameField.getText().trim());
                product.setCategoryId(Integer.parseInt(categoryField.getText().trim()));
                product.setSupplierId(Integer.parseInt(supplierField.getText().trim()));
                product.setPrice(Double.parseDouble(priceField.getText().trim()));
                product.setStock(Integer.parseInt(stockField.getText().trim()));

                productController.updateProduct(product);
                JOptionPane.showMessageDialog(this, "Product updated");
                loadProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        }
    }
}
