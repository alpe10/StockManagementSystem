package org.example.view;

import javax.swing.*;
import java.awt.*;

public class WarehouseDashboard extends JFrame {

    public WarehouseDashboard(){

        setTitle("Warehouse Manager Panel");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel header = new JLabel("Warehouse Dashboard",SwingConstants.CENTER);
        header.setFont(new Font("Arial",Font.BOLD,22));

        add(header,BorderLayout.NORTH);

        JPanel sidebar = new JPanel(new GridLayout(6,1,10,10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton productBtn = new JButton("Products");
        JButton stockBtn = new JButton("Stock In/Out");
        JButton criticalBtn = new JButton("Kritik Stok");
        JButton movementBtn = new JButton("Hareketler");
        JButton supplierBtn = new JButton("Suppliers");
        JButton logoutBtn = new JButton("Logout");

        sidebar.add(productBtn);
        sidebar.add(stockBtn);
        sidebar.add(criticalBtn);
        sidebar.add(movementBtn);
        sidebar.add(supplierBtn);
        sidebar.add(logoutBtn);

        add(sidebar,BorderLayout.WEST);

        // Content area with card layout similar to Admin dashboard
        CardLayout cardLayout = new CardLayout();
        JPanel content = new JPanel(cardLayout);

        content.add(new ProductPanel(), "products");
        content.add(new StockAdjustPanel(), "stock");
        content.add(new LowStockPanel(10), "critical");
        content.add(new MovementPanel(), "movements");
        content.add(new SupplierPanel(), "suppliers");

        add(content,BorderLayout.CENTER);

        productBtn.addActionListener(e -> cardLayout.show(content,"products"));
        stockBtn.addActionListener(e -> cardLayout.show(content,"stock"));
        criticalBtn.addActionListener(e -> cardLayout.show(content,"critical"));
        movementBtn.addActionListener(e -> cardLayout.show(content,"movements"));
        supplierBtn.addActionListener(e -> cardLayout.show(content,"suppliers"));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

    }
}
