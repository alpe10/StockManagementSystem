package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService = new UserService();

    public void register(User user){
        userService.register(user);
    }

    public User login(String username,String password){
        return userService.login(username,password);
    }

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    public void activateUser(int id){
        userService.activateUser(id);
    }

    public void deactivateUser(int id){
        userService.deactivateUser(id);
    }

    public void deleteUser(int id){
        userService.deleteUser(id);
    }
}