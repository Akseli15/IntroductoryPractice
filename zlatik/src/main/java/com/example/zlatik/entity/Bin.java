package com.example.zlatik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Bin {
    private @Id @GeneratedValue Long id;
    private int quantity;
    private double shippingcost;

    public Bin() {
    }

    public Bin(Long id, int quantity, double shippingcost) {
        this.id = id;
        this.quantity = quantity;
        this.shippingcost = shippingcost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getShippingcost() {
        return shippingcost;
    }

    public void setShippingcost(double shippingcost) {
        this.shippingcost = shippingcost;
    }
}
