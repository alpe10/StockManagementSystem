package org.example.service;

import org.example.dao.StockMovementDAO;
import org.example.model.StockMovement;

import java.util.List;

public class StockMovementService {

    private final StockMovementDAO movementDAO = new StockMovementDAO();

    public void addMovement(StockMovement movement) {
        movementDAO.addMovement(movement);
    }

    public List<StockMovement> getAllMovements() {
        return movementDAO.getAllMovements();
    }
}
