package com.example.zlatik.controller;

import com.example.zlatik.entity.Product;
import com.example.zlatik.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AllRequest {
    @Autowired
    ProductService productService;
    @GetMapping("")
    public String test(Model model){
        ProductService allList;
        model.addAttribute("list",productService.getList());
        return "mainPage";
    }
    @GetMapping("{id}")
    public String getProduct(@PathVariable("id")String id, Model model){
        ProductService editObject = new ProductService();
        List<Product> arr = new ArrayList<>();
        arr.add(editObject.getObjectByID(id));
        model.addAttribute("list",arr);
        return "editProduct";
    }
    @PostMapping("")
    public String createNewProduct(){
        productService.saveNewObject();
        System.out.println("Create new object");
        return "redirect:/";
    }
    @PostMapping("{id}")
    public String edit_object(@PathVariable("id")String id, @RequestParam(value = "name",required = false)String name, @RequestParam(value = "category",required = false)String category, @RequestParam(value = "unitPrice",required = false)String unitPrice, @RequestParam(value = "stockBalance",required = false)String stockBalance, @RequestParam(value = "shippingCost",required = false)String shippingCost , @RequestParam(value = "rating",required = false)String rating,@RequestParam(value = "discount",required = false)String discount){
        ProductService editObject;
        if(productService.saveEdit(id,name,category,unitPrice,stockBalance,shippingCost,rating,discount)){
            return "redirect:/ERROR";
        }
        System.out.println("Save object");
        return "redirect:/";
    }
    @GetMapping("ERROR")
    public String error(){
        return "test";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")String id){
        ProductService deleteProduct=new ProductService();
        deleteProduct.deleteProduct(id);
        return "redirect:/";
    }
}