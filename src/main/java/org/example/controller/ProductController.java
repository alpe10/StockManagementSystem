package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;

import java.util.List;

public class ProductController {

    private ProductService productService = new ProductService();

    public int addProduct(Product product){
        return productService.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    public void deleteProduct(int productId) {
        productService.deleteProduct(productId);
    }

    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    public List<Product> getLowStockProducts(int threshold) {
        return productService.getLowStockProducts(threshold);
    }
}
