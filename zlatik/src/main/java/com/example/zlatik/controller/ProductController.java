package com.example.zlatik.controller;

import com.example.zlatik.entity.Product;
import com.example.zlatik.service.BinService;
import com.example.zlatik.service.IProductService;
import jakarta.jws.WebParam;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    IProductService productService;
    @Autowired
    BinService binService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //admin
    @Async
    @GetMapping("")
    public String test(Model model) {
        model.addAttribute("list", productService.getList());
        return "mainPage";
    }

    @Async
    @PostMapping("/create")
    public String createNewProduct() {
        productService.saveNewProduct();
        System.out.println("Create new object");
        return "redirect:/";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getProduct(@PathVariable("id") String id, Model model) {
        List<Product> arr = new ArrayList<>();
        arr.add(productService.getProductID(id));
        model.addAttribute("product", arr);
        return "editProduct";
    }

    @Async
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "category", required = false) String category,
                               @RequestParam(value = "unitPrice", required = false) String unitPrice,
                               @RequestParam(value = "stockBalance", required = false) String stockBalance,
                               @RequestParam(value = "shippingCost", required = false) String shippingCost,
                               @RequestParam(value = "rating", required = false) String rating,
                               @RequestParam(value = "discount", required = false) String discount) {
        Product product = new Product(
                Long.parseLong(id),
                name,
                category,
                Integer.parseInt(unitPrice),
                Integer.parseInt(stockBalance),
                Integer.parseInt(shippingCost),
                Double.parseDouble(rating),
                Double.parseDouble(discount));

        productService.update(product);
        System.out.println("Save object controller");
        return "redirect:/";
    }

    @Async
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
    //Business
    @Async
    @GetMapping("/market")
    public String getListProduct(Model model){
        model.addAttribute("product",productService.getList());
        return "businessPage";
    }
    @Async
    @GetMapping("addToBin/{id}")
    public String addToBin(@PathVariable("id") String id){
        binService.addNewBin(id);
        return "redirect:/market";
    }
    //Bin
    @Async
    @GetMapping("bin")
    public String getBinData(Model model){
        model.addAttribute("product",binService.getBinProduct());
        model.addAttribute("totalPrice",binService.totalPrice());
        return "binPage";
    }
    @Async
    @GetMapping("sale")
    public String sale(){
        binService.doSale();
        return "redirect:/market";
    }
    @Async
    @PostMapping("/bin/edit/{id}")
    public String editBin(@PathVariable("id") String id,@RequestParam(value = "quantity", required = false) String quantity){
        System.out.println(quantity);
        if(binService.editQuantity(id,quantity)){
            return "redirect:/bin";
        }
        return "redirect:/error";
    }

    @Async
    @GetMapping("error")
    public String error() {
        return "test";
    }
}