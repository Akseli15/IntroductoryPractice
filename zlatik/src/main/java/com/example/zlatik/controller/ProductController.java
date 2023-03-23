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
    @PostMapping("")
    public Order sellProduct(@RequestBody SaleRequest saleRequest) {
        Long productId = saleRequest.getProductId();
        int quantity = saleRequest.getStockBalance();
        Product product = productService.getProductById(productId);
        int availableStock = product.getStockBalance();

        if (availableStock < quantity) {
            throw new OutOfStockException("Product is out of stock.");
        }

        double unitPrice = product.getUnitPrice();
        double discount = product.getDiscount();
        double shippingCost = product.getShippingCost();

        double totalPrice = (unitPrice * quantity) * (1 - (discount / 100)) + shippingCost;

        product.setStockBalance(availableStock - quantity);
        productService.update(Product, product);

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setUnitPrice(unitPrice);
        order.setDiscount(discount);
        order.setShippingCost(shippingCost);
        order.setTotalPrice(totalPrice);

        return order;
    }

    @Async
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
    }
}