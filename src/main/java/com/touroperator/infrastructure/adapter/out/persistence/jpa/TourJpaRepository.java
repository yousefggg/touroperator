package com.touroperator.infrastructure.adapter.out.persistence.jpa;

import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourJpaRepository extends JpaRepository<TourEntity, Long> {
}
