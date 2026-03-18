package org.example.view;

import org.example.controller.ProductController;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel for stock in/out operations; only stock value is modified.
 */
public class StockAdjustPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final JButton stockInBtn;
    private final JButton stockOutBtn;
    private final JButton refreshBtn;

    private final ProductController productController = new ProductController();

    public StockAdjustPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {"ID", "Name", "Category ID", "Supplier ID", "Price", "Stock"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        stockInBtn = new JButton("Stock In");
        stockOutBtn = new JButton("Stock Out");
        refreshBtn = new JButton("Refresh");
        controls.add(stockInBtn);
        controls.add(stockOutBtn);
        controls.add(refreshBtn);
        add(controls, BorderLayout.NORTH);

        stockInBtn.addActionListener(e -> adjustStock(true));
        stockOutBtn.addActionListener(e -> adjustStock(false));
        refreshBtn.addActionListener(e -> loadProducts());

        loadProducts();
    }

    private void loadProducts() {
        model.setRowCount(0);
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

    private void adjustStock(boolean increase) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product first.");
            return;
        }

        String prompt = increase ? "Enter quantity to add:" : "Enter quantity to remove:";
        String input = JOptionPane.showInputDialog(this, prompt, "Stock Adjustment", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return; // cancelled

        try {
            int qty = Integer.parseInt(input.trim());
            if (qty <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be positive.");
                return;
            }

            int currentStock = Integer.parseInt(model.getValueAt(row, 5).toString());
            int newStock = increase ? currentStock + qty : currentStock - qty;
            if (newStock < 0) {
                JOptionPane.showMessageDialog(this, "Stock cannot go below zero.");
                return;
            }

            // Reuse existing values to build product object
            Product product = new Product();
            product.setProductId((int) model.getValueAt(row, 0));
            product.setProductName(model.getValueAt(row, 1).toString());
            product.setCategoryId(Integer.parseInt(model.getValueAt(row, 2).toString()));
            product.setSupplierId(Integer.parseInt(model.getValueAt(row, 3).toString()));
            product.setPrice(Double.parseDouble(model.getValueAt(row, 4).toString()));
            product.setStock(newStock);

            productController.updateProduct(product);
            JOptionPane.showMessageDialog(this, "Stock updated to " + newStock);
            loadProducts();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number entered.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating stock: " + ex.getMessage());
        }
    }
}
