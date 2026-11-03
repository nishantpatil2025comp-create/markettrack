package com.markettrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double purchasePrice;
    private double sellingPrice;
    private int initialStock;
    private int soldQuantity;

    public Product() {}

    public Product(String name, double purchasePrice, double sellingPrice, int initialStock, int soldQuantity) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.initialStock = initialStock;
        this.soldQuantity = soldQuantity;
    }

    public Product(Long id, String name, double purchasePrice, double sellingPrice, int initialStock, int soldQuantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.initialStock = initialStock;
        this.soldQuantity = soldQuantity;
    }

    // ==========================================================
    // Calculated Business Logic & Controller Alias Methods
    // ==========================================================

    @Transient
    public int getRemainingStock() {
        return Math.max(0, this.initialStock - this.soldQuantity);
    }

    @Transient
    public double getTotalInventoryValue() {
        return getRemainingStock() * this.purchasePrice;
    }

    @Transient
    public double getInventoryValue() {
        return getTotalInventoryValue();
    }

    @Transient
    public double getTotalSalesRevenue() {
        return this.soldQuantity * this.sellingPrice;
    }

    @Transient
    public double getSalesRevenue() {
        return getTotalSalesRevenue();
    }

    @Transient
    public double getTotalProfit() {
        return (this.sellingPrice - this.purchasePrice) * this.soldQuantity;
    }

    @Transient
    public double getNetProfit() {
        return getTotalProfit();
    }

    @Transient
    public boolean isLowStock() {
        return getRemainingStock() < 5;
    }

    // ==========================================================
    // Standard Getters and Setters
    // ==========================================================

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }

    public double getPurchasePrice() { 
        return purchasePrice; 
    }
    
    public void setPurchasePrice(double purchasePrice) { 
        this.purchasePrice = purchasePrice; 
    }

    public double getSellingPrice() { 
        return sellingPrice; 
    }
    
    public void setSellingPrice(double sellingPrice) { 
        this.sellingPrice = sellingPrice; 
    }

    public int getInitialStock() { 
        return initialStock; 
    }
    
    public void setInitialStock(int initialStock) { 
        this.initialStock = initialStock; 
    }

    public int getSoldQuantity() { 
        return soldQuantity; 
    }
    
    public void setSoldQuantity(int soldQuantity) { 
        this.soldQuantity = soldQuantity; 
    }
}