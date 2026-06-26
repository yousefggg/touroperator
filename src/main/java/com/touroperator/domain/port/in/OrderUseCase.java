package com.touroperator.domain.port.in;

import com.touroperator.application.dto.command.CreateOrderCommand;
import com.touroperator.domain.model.Order;

import java.util.List;

public interface OrderUseCase {

    Order createOrder(CreateOrderCommand command);

    Order getOrderById(Long id);

    List<Order> getUserOrders(Long userId);

    void confirmOrder(Long orderId);

    void cancelOrder(Long orderId);
}