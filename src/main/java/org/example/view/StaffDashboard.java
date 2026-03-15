package org.example.view;

import javax.swing.*;
import java.awt.*;

public class StaffDashboard extends JFrame {

    public StaffDashboard(){

        setTitle("Staff Dashboard");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel header = new JLabel("Staff Panel",SwingConstants.CENTER);
        header.setFont(new Font("Arial",Font.BOLD,22));

        add(header,BorderLayout.NORTH);

        JPanel sidebar = new JPanel(new GridLayout(4,1,10,10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton productBtn = new JButton("Products");
        JButton stockBtn = new JButton("Stock In/Out");
        JButton logoutBtn = new JButton("Logout");

        sidebar.add(productBtn);
        sidebar.add(stockBtn);
        sidebar.add(new JLabel());
        sidebar.add(logoutBtn);

        add(sidebar,BorderLayout.WEST);

        CardLayout cardLayout = new CardLayout();
        JPanel content = new JPanel(cardLayout);
        content.add(new ReadOnlyProductPanel(), "products");
        content.add(new StockAdjustPanel(), "stock");

        add(content,BorderLayout.CENTER);

        productBtn.addActionListener(e -> cardLayout.show(content,"products"));
        stockBtn.addActionListener(e -> cardLayout.show(content,"stock"));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

    }
}
