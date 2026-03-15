package org.example.service;

import org.example.dao.SupplierDAO;
import org.example.model.Supplier;

import java.util.List;

public class SupplierService {

    private SupplierDAO supplierDAO = new SupplierDAO();

    public void addSupplier(Supplier supplier){
        supplierDAO.addSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDAO.getAllSuppliers();
    }
}