package org.example.dao;

import org.example.model.Supplier;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public void addSupplier(Supplier supplier){

        String sql = "INSERT INTO suppliers (supplier_name, phone, email) VALUES (?,?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Supplier> getAllSuppliers(){

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Supplier s = new Supplier();

                s.setSupplierId(rs.getInt("supplier_id"));
                s.setSupplierName(rs.getString("supplier_name"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));

                suppliers.add(s);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return suppliers;
    }
}