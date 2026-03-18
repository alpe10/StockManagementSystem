package org.example.view;

import javax.swing.*;
import java.awt.*;

import org.example.controller.UserController;
import org.example.model.User;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton registerButton;

    private UserController controller = new UserController();

    public RegisterFrame(){

        setTitle("Register");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        roleBox = new JComboBox<>(new String[]{
                "staff",
                "warehouse_manager"
        });

        registerButton = new JButton("Register");

        panel.add(new JLabel("Username"));
        panel.add(usernameField);

        panel.add(new JLabel("Password"));
        panel.add(passwordField);

        panel.add(new JLabel("Role"));
        panel.add(roleBox);

        panel.add(new JLabel());
        panel.add(registerButton);

        add(panel);

        registerButton.addActionListener(e -> registerUser());
    }

    private void registerUser(){

        try{

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please fill all fields");
                return;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            // Kullanıcı pasif olarak kayıt edilir
            user.setStatus(false);

            controller.register(user);

            JOptionPane.showMessageDialog(this,
                    "User registered successfully.\nWaiting for admin approval.");

            dispose();

        }catch(Exception ex){

            JOptionPane.showMessageDialog(this,"Registration Error");
            ex.printStackTrace();

        }

    }
}