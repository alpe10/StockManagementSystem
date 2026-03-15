package org.example.view;

import org.example.controller.ProductController;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LowStockPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final ProductController productController = new ProductController();
    private final int threshold;

    public LowStockPanel(int threshold) {
        this.threshold = threshold;
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Category ID", "Supplier ID", "Price", "Stock"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);

        // renderer for red text
        DefaultTableCellRenderer redRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(Color.RED);
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, redRenderer);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> loadData());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Threshold: " + threshold));
        top.add(refreshBtn);
        add(top, BorderLayout.NORTH);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Product> low = productController.getLowStockProducts(threshold);
        for (Product p : low) {
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
