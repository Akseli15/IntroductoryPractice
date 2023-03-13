package com.example.zlatik.repository;
import com.example.zlatik.entity.Product;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class XMLRepo {
    private final static String fileName = "";

    public static Optional<Product> findById(Long myClassId) {
        List<Product> myClassList= new ArrayList<>();
    }

    public static void delete(Long myClassId) {
    }

    public List<Product> findAll(){
    }

    public void save(Product product, int x) {
    }

    public static Product findByID(long myClassId) {
    }
}

