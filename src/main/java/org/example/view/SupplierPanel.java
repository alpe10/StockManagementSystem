package org.example.view;

import org.example.controller.SupplierController;
import org.example.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {

    private final JTextField nameField;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JButton addButton;
    private final JTable table;
    private final DefaultTableModel model;

    private final SupplierController supplierController = new SupplierController();

    public SupplierPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("Supplier Name"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Phone"));
        phoneField = new JTextField();
        form.add(phoneField);

        form.add(new JLabel("Email"));
        emailField = new JTextField();
        form.add(emailField);

        addButton = new JButton("Add Supplier");
        form.add(new JLabel());
        form.add(addButton);

        add(form, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Name", "Phone", "Email"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        addButton.addActionListener(e -> addSupplier());

        loadSuppliers();
    }

    private void addSupplier() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter supplier name.");
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setSupplierName(name);
        supplier.setPhone(phone);
        supplier.setEmail(email);

        supplierController.addSupplier(supplier);

        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");

        loadSuppliers();
    }

    private void loadSuppliers() {
        model.setRowCount(0);
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        for (Supplier s : suppliers) {
            model.addRow(new Object[]{
                    s.getSupplierId(),
                    s.getSupplierName(),
                    s.getPhone(),
                    s.getEmail()
            });
        }
    }
}
