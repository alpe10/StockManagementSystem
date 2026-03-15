package org.example.view;

import org.example.controller.ProductController;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Displays products in read-only mode for staff users.
 */
public class ReadOnlyProductPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final ProductController productController = new ProductController();

    public ReadOnlyProductPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Category ID", "Supplier ID", "Price", "Stock"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> loadProducts());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(refreshBtn);
        add(top, BorderLayout.NORTH);

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
}
