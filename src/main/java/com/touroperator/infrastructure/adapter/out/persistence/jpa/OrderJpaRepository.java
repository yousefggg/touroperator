package com.touroperator.infrastructure.adapter.out.persistence.jpa;

import com.touroperator.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}