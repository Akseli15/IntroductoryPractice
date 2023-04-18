package com.example.zlatik.controller.DTO;

public class BinDTO {
    private Long id;
    private String productName;
    private int quantity;
    private int getShippingCost;
    private int unitPrice;
    private int stockBalance;

    public BinDTO() {
    }

    public BinDTO(Long id, String productName, int quantity, int getShippingCost, int unitPrice, int stockBalance) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.getShippingCost = getShippingCost;
        this.unitPrice = unitPrice;
        this.stockBalance = stockBalance;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getGetShippingCost() {
        return getShippingCost;
    }

    public void setGetShippingCost(int getShippingCost) {
        this.getShippingCost = getShippingCost;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
