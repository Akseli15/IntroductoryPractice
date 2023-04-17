package com.example.zlatik.service;

import com.example.zlatik.entity.Product;
import java.util.List;

public interface IProductService {
    List<Product> getList();
    void saveNewProduct();
    void deleteProduct(String id);
    Product getProductID(String id);
    Product update(Product product);
}

