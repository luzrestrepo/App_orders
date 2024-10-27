package com.luzrestrepo.app_orders.controller;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.luzrestrepo.app_orders.entity.Product;
import com.luzrestrepo.app_orders.service.ProductService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String showProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "products";
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product geProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }
}
