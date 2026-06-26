package com.touroperator.domain.port.out;

import com.touroperator.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);

    void deleteById(Long id);
}