package org.example.service;

import org.example.dao.OrderDAO;
import org.example.model.Order;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();

    public void addOrder(Order order){
        orderDAO.addOrder(order);
    }
}