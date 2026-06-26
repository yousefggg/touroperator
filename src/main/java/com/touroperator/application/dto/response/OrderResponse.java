package com.touroperator.application.dto.response;

import com.touroperator.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Long userId;
    private Long tourId;
    private Order.Status status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
}