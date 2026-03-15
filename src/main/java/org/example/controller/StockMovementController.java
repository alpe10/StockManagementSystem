package org.example.controller;

import org.example.model.StockMovement;
import org.example.service.StockMovementService;

import java.util.List;

public class StockMovementController {

    private final StockMovementService movementService = new StockMovementService();

    public void addMovement(StockMovement movement) {
        movementService.addMovement(movement);
    }

    public List<StockMovement> getAllMovements() {
        return movementService.getAllMovements();
    }
}
