package com.example.zlatik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int unitPrice;
    private int stockBalance;
    private int shippingCost;
    private double rating;
    private double discount;
    public Product() {
        this.id=Long.parseLong("-1");
        this.name = null;
        this.category = null;
        this.unitPrice = 0;
        this.stockBalance = 0;
        this.shippingCost = 0;
        this.rating = 0;
        this.discount = 0;
    }

    public Product(Long id, String name, String category, int unitPrice, int stockBalance,
                   int shippingCost, double rating, double discount) {
        this.id=id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stockBalance = stockBalance;
        this.shippingCost = shippingCost;
        this.rating = rating;
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }
    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public int getUnitPrice() {
        return unitPrice;
    }
    public int getStockBalance() {
        return stockBalance;
    }
    public int getShippingCost() {
        return shippingCost;
    }
    public double getRating() {
        return rating;
    }
    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", unitPrice=" + unitPrice +
                ", stockBalance=" + stockBalance +
                ", shippingCost=" + shippingCost +
                ", rating=" + rating +
                ", discount=" + discount +
                '}';
    }
}