package com.luzrestrepo.app_orders.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.luzrestrepo.app_orders.entity.Product;
import com.luzrestrepo.app_orders.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProduct(Integer id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
