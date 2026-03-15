package org.example.view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public AdminDashboard(){

        setTitle("Admin Dashboard");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // HEADER
        JLabel header = new JLabel("Stock Management System", JLabel.CENTER);
        header.setFont(new Font("Arial",Font.BOLD,22));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(header,BorderLayout.NORTH);

        // SIDEBAR
        JPanel sidebar = new JPanel(new GridLayout(6,1,10,10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        sidebar.setPreferredSize(new Dimension(200,0));

        JButton productBtn = new JButton("Products");
        JButton categoryBtn = new JButton("Categories");
        JButton supplierBtn = new JButton("Suppliers");
        JButton userBtn = new JButton("Users");
        JButton logoutBtn = new JButton("Logout");

        sidebar.add(productBtn);
        sidebar.add(categoryBtn);
        sidebar.add(supplierBtn);
        sidebar.add(userBtn);
        sidebar.add(new JLabel());
        sidebar.add(logoutBtn);

        add(sidebar,BorderLayout.WEST);

        // CONTENT PANEL
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new ProductPanel(),"products");
        contentPanel.add(new CategoryPanel(),"categories");
        contentPanel.add(new SupplierPanel(),"suppliers");
        contentPanel.add(new UserManagementPanel(),"users");

        add(contentPanel,BorderLayout.CENTER);

        // BUTTON EVENTS
        productBtn.addActionListener(e -> cardLayout.show(contentPanel,"products"));
        categoryBtn.addActionListener(e -> cardLayout.show(contentPanel,"categories"));
        supplierBtn.addActionListener(e -> cardLayout.show(contentPanel,"suppliers"));
        userBtn.addActionListener(e -> cardLayout.show(contentPanel,"users"));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

    }
}