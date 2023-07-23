package com.tms.estore.repository;

import com.tms.estore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByUserId(Long id);

    boolean existsByName(String number);

    Order findOrderById(Long id);
}
