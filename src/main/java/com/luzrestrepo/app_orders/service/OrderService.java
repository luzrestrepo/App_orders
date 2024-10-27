package com.luzrestrepo.app_orders.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.luzrestrepo.app_orders.entity.Order;
import com.luzrestrepo.app_orders.entity.Product;
import com.luzrestrepo.app_orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public List <Order> createOrder(List<Integer> productIds){
        List<Order> orders = new ArrayList<>();

        for(Integer productId: productIds){
            Product product = productService.getProduct(productId);
            Order order = new Order();
            order.setProduct(product);
            if(product.getCurrentStock()> 0){
                product.setCurrentStock(product.getCurrentStock()-1);
                productService.saveProduct(product);
                orders.add(orderRepository.save(order));
            }else {
                throw new RuntimeException("producto sin estock"+ product.getName());
            }
        }
        return orders;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
