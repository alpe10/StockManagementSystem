package org.example.view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.AdminDashboard;
import org.example.view.RegisterFrame;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame(){

        setTitle("Stock Management System - Login");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(registerButton);
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> openRegister());
    }
    private void openRegister(){

        new RegisterFrame().setVisible(true);

    }

    private void login(){

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserController controller = new UserController();
        User user = controller.login(username,password);

        // Kullanıcı yoksa
        if(user == null){
            JOptionPane.showMessageDialog(this,"Username or password incorrect");
            return;
        }

        // Kullanıcı pasifse
        if(!user.isStatus()){
            JOptionPane.showMessageDialog(this,"Your account is inactive. Wait for admin approval.");
            return;
        }

        // Rol kontrolü
        if(user.getRole().equalsIgnoreCase("admin")){

            new AdminDashboard().setVisible(true);
            dispose();

        }else if(user.getRole().equalsIgnoreCase("staff")){

            new StaffDashboard().setVisible(true);
            dispose();

        }else if(user.getRole().equalsIgnoreCase("warehouse_manager")){

            new WarehouseDashboard().setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}