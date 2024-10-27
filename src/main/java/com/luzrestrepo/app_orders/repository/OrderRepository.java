package com.luzrestrepo.app_orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.luzrestrepo.app_orders.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
