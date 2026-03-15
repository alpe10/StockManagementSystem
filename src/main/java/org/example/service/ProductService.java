package org.example.service;

import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.model.StockMovement;
import org.example.service.StockMovementService;

import java.time.LocalDateTime;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();
    private StockMovementService movementService = new StockMovementService();

    public int addProduct(Product product){
        int id = productDAO.addProduct(product);
        if (id > 0) {
            StockMovement m = new StockMovement();
            m.setProductId(id);
            m.setType("Giriş");
            m.setQuantity(product.getStock());
            m.setDate(LocalDateTime.now());
            movementService.addMovement(m);
        }
        return id;
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    public void updateProduct(Product product) {
        Product current = productDAO.getProductById(product.getProductId());
        productDAO.updateProduct(product);

        if (current != null) {
            int delta = product.getStock() - current.getStock();
            if (delta != 0) {
                StockMovement m = new StockMovement();
                m.setProductId(product.getProductId());
                m.setType(delta > 0 ? "Giriş" : "Çıkış");
                m.setQuantity(Math.abs(delta));
                m.setDate(LocalDateTime.now());
                movementService.addMovement(m);
            }
        }
    }

    public List<Product> getLowStockProducts(int threshold) {
        return productDAO.getLowStockProducts(threshold);
    }
}
