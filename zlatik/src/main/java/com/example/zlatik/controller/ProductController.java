package com.example.zlatik.controller;

import com.example.zlatik.entity.Product;
import com.example.zlatik.service.IProductService;
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

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
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
                String.join(name),
                String.join(category),
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
    @GetMapping("error")
    public String error() {
        return "test";
    }
    @Async
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
    @Async
    @PostMapping("totalPrice")
    public String totalPrice(@PathVariable("id") String id,
                             @RequestParam(value = "stockBalance", required = false) String stockBalance) {
        double sum = productService.totalPrice(Long.parseLong(id),Integer.parseInt(stockBalance));
        return "redirect:/totalPrice/"+id+"/"+sum;
    }

    /* @Async
    @GetMapping("totalPrice/{id}/{sum}")
    public String setTotalPrice(@PathVariable("id") String id, @PathVariable("sum") String sum){

    } */

    @Async
    @PostMapping("sellProduct")
    public String sellProduct(@PathVariable("id") String id,
                              @RequestParam(value = "stockBalance", required = false) String stockBalance) {

        int quantity = Integer.parseInt(stockBalance);
        Product product = productService.getProductID(id);
        int availableStock = product.getStockBalance();

        if (!productService.doSale(Long.parseLong(id),Integer.parseInt(stockBalance))) {
            return "error";
        }
        product.setStockBalance(product.getStockBalance()-Integer.parseInt(stockBalance));
        productService.update(product);
        return "redirect:/";
    }

    /* @Async
    @PostMapping("/replenish/{id}")
    public Stock replenishStock(@PathVariable("id") Long id, @RequestBody StockUpdateRequest stockUpdateRequest) {
        Product product = productService.getProductID(id);
        int currentStock = product.getStockBalance();
        int quantityToAdd = stockUpdateRequest.getQuantity();
        int newStock = currentStock + quantityToAdd;
        product.setStockBalance(newStock);
        productService.updateProduct(id, product);

        Stock stock = new Stock();
        stock.setProductId(id);
        stock.setQuantity(newStock);

        return stock;
    } */
}