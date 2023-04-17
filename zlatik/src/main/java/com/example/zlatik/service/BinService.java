package com.example.zlatik.service;

import com.example.zlatik.entity.Bin;
import com.example.zlatik.entity.Product;
import com.example.zlatik.repository.BinRepository;
import com.example.zlatik.repository.IProductRepository;
import com.example.zlatik.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BinService {

    @Autowired
    IProductRepository jsonRepo;
    BinRepository binRepository;

    @Async
    public double totalPrice(Long id, int quantity){
        List<Bin> allBin=binRepository.findAll();
        double totalPrice = 0;
        for (int i=0;i<allBin.size();i++){
            Product product = jsonRepo.getByID(allBin.get(i).getId());
            totalPrice += (product.getUnitPrice() * quantity) * (1 - (product.getDiscount() / 100)) + product.getShippingCost();
        }
        return totalPrice;
    }

    @Async
    public boolean doSale(Long id, int stockBalance){
        Product product = jsonRepo.getByID(id);
        if (product.getStockBalance() < stockBalance) {
            return false;
        }
        binRepository.deleteAll();
        return true;
    }
}
