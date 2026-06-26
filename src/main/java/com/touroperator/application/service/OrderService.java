package com.touroperator.application.service;

import com.touroperator.application.dto.command.CreateOrderCommand;
import com.touroperator.domain.model.Order;
import com.touroperator.domain.port.in.OrderUseCase;
import com.touroperator.domain.port.out.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderUseCase {
    @Override
    public Order createOrder(CreateOrderCommand command) {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return List.of();
    }

    @Override
    public void confirmOrder(Long orderId) {

    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
