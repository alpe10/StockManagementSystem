package org.example.model;

public class Product {

    private int productId;
    private String productName;
    private int categoryId;
    private int supplierId;
    private double price;
    private int stock;

    public Product(){}

    public Product(int productId, String productName, int categoryId, int supplierId, double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
