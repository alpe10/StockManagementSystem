package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public void register(User user){
        userDAO.register(user);
    }

    public User login(String username,String password){
        return userDAO.login(username,password);
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void activateUser(int id){
        userDAO.activateUser(id);
    }

    public void deactivateUser(int id){
        userDAO.deactivateUser(id);
    }

    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
}