package org.example.view;

import org.example.controller.UserController;
import org.example.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JButton activateBtn;
    private JButton deactivateBtn;
    private JButton deleteBtn;

    private UserController controller = new UserController();

    public UserManagementPanel(){

        setLayout(new BorderLayout());

        String[] columns = {"ID","Username","Role","Status"};

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        activateBtn = new JButton("Activate");
        deactivateBtn = new JButton("Deactivate");
        deleteBtn = new JButton("Delete");

        buttonPanel.add(activateBtn);
        buttonPanel.add(deactivateBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        activateBtn.addActionListener(e -> activateUser());
        deactivateBtn.addActionListener(e -> deactivateUser());
        deleteBtn.addActionListener(e -> deleteUser());

        loadUsers();
    }

    private void loadUsers(){

        model.setRowCount(0);

        List<User> users = controller.getAllUsers();

        for(User user : users){

            String status = user.isStatus() ? "Active" : "Passive";

            model.addRow(new Object[]{
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole(),
                    status
            });
        }
    }

    private void activateUser(){

        int row = table.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a user");
            return;
        }

        int id = (int) model.getValueAt(row,0);

        controller.activateUser(id);

        loadUsers();
    }

    private void deactivateUser(){

        int row = table.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a user");
            return;
        }

        int id = (int) model.getValueAt(row,0);

        controller.deactivateUser(id);

        loadUsers();
    }

    private void deleteUser(){

        int row = table.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a user");
            return;
        }

        int id = (int) model.getValueAt(row,0);

        controller.deleteUser(id);

        loadUsers();
    }
}