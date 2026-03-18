package org.example.view;

import org.example.controller.StockMovementController;
import org.example.model.StockMovement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovementPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final StockMovementController controller = new StockMovementController();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MovementPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Product ID", "Type", "Quantity", "Date"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> loadData());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(refreshBtn);
        add(top, BorderLayout.NORTH);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<StockMovement> movements = controller.getAllMovements();
        for (StockMovement m : movements) {
            model.addRow(new Object[]{
                    m.getId(),
                    m.getProductId(),
                    m.getType(),
                    m.getQuantity(),
                    m.getDate() != null ? formatter.format(m.getDate()) : ""
            });
        }
    }
}
