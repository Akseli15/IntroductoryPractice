package com.example.zlatik.service;

import com.example.zlatik.controller.DTO.BinDTO;
import com.example.zlatik.entity.Bin;
import com.example.zlatik.entity.Product;
import com.example.zlatik.repository.BinRepository;
import com.example.zlatik.repository.IProductRepository;
import com.example.zlatik.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BinService {

    @Autowired
    IProductRepository jsonRepo;
    @Autowired
    BinRepository binRepository;

    @Async
    public double totalPrice(){
        List<Bin> allBin=binRepository.findAll();
        double totalPrice = 0;
        for (int i=0;i<allBin.size();i++){
            Product product = jsonRepo.getByID(allBin.get(i).getId());
            totalPrice += (product.getUnitPrice() * allBin.get(i).getQuantity()) * (1 - (product.getDiscount() / 100)) + product.getShippingCost();
        }
        return totalPrice;
    }
    @Async
    public List<BinDTO> getBinProduct(){
        List<BinDTO> products = new ArrayList<>();
        List<Bin> allBin=binRepository.findAll();
        for (int i=0;i<allBin.size();i++){
            Product product = jsonRepo.getByID(allBin.get(i).getId());
            BinDTO binDTO = new BinDTO(product.getId(),
                    product.getName(),
                    allBin.get(i).getQuantity(),
                    product.getShippingCost(),
                    product.getUnitPrice(),
                    product.getStockBalance());
            products.add(binDTO);
        }
        return products;
    }
    @Async
    public void addNewBin(String id){
        Bin bin = new Bin(Long.parseLong(id),1);
        binRepository.save(bin);
    }

    @Async
    public void doSale(){
        List<Bin> binList = binRepository.findAll();
        for (int i=0;i<binList.size();i++){
            Product product = jsonRepo.getByID(binList.get(i).getId());
            product.setStockBalance(product.getStockBalance()-binList.get(i).getQuantity());
            jsonRepo.update(product);
        }
        binRepository.deleteAll();
    }
    @Async
    public boolean editQuantity(String id,String quantity){
        Optional<Bin> bin = binRepository.findById(Long.parseLong(id));
        if(jsonRepo.getByID(bin.get().getId()).getStockBalance()<Integer.parseInt(quantity)){
            return false;
        }
        bin.get().setQuantity(Integer.parseInt(quantity));
        binRepository.save(bin.get());
        return true;
    }
}
