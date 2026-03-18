package org.example.controller;

import org.example.model.Supplier;
import org.example.service.SupplierService;

import java.util.List;

public class SupplierController {

    private SupplierService supplierService = new SupplierService();

    public void addSupplier(Supplier supplier){
        supplierService.addSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
}