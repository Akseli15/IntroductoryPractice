package com.example.zlatik.service;

import com.example.zlatik.entity.Product;
import com.example.zlatik.repository.JSONRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    JSONRepo jsonRepo = new JSONRepo();

    public List<Product> getList(){
        return jsonRepo.findAll();
    }

    public void saveNewObject(){
        Product newP =new Product();
        jsonRepo.save(newP,1);
        System.out.println("Create new empty object");
    }

    public void deleteProduct(String id){
        jsonRepo.delete(Long.parseLong(id));
    }

    public Product getObjectByID(String id){
        return jsonRepo.getByID(Long.parseLong(id));
    }
    public boolean saveEdit(String id, String name, String category, String unitPrice, String stockBalance, String shippingCost, String rating, String discount) {
        Product product = jsonRepo.getByID(Long.parseLong(id));
        JSONRepo.delete(Long.parseLong(id));

        if(name.equals("")){
        }
        else{
            product.setName(name);
        }

        if(category.equals("")){
        }
        else{
            product.setCategory(category);
        }

        if(unitPrice.equals("")){
        }
        else{
            product.setUnitPrice(Integer.parseInt(unitPrice));
        }

        if(stockBalance.equals("")){
        }
        else{
            product.setStockBalance(Integer.parseInt(stockBalance));
        }

        if(shippingCost.equals("")){
        }
        else{
            product.setShippingCost(Integer.parseInt(shippingCost));
        }

        if(rating.equals("")){
        }
        else{
            if(Integer.parseInt(rating)>10 | Integer.parseInt(rating)<0){
                return true;
            }
            product.setRating(Integer.parseInt(rating));
        }

        if(discount.equals("")){
        }
        else{
            if(Integer.parseInt(discount)>100 | Integer.parseInt(discount)<0){
                return true;
            }
            product.setDiscount(Integer.parseInt(discount));
        }

        jsonRepo.save(product,0);
        return false;
    }
}
