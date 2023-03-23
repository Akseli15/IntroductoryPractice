package com.example.zlatik.repository;

import com.example.zlatik.entity.Product;
import java.util.List;

public interface IProductRepository {
    Product getByID(Long myClassId);
    void delete(Long myClassId);
    void save(Product x);
    List<Product> findAll();
    Product update(Product product);
}
