package com.touroperator.infrastructure.adapter.out.persistence.jpa;

import com.touroperator.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
