package com.touroperator.infrastructure.adapter.out.persistence.mapper;

import com.touroperator.domain.model.User;
import com.touroperator.infrastructure.adapter.out.persistence.entity.UserEntity;

public final class UserMapper {

    private UserMapper() {}

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getRole()
        );
    }

    public static UserEntity toEntityForCreate(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();

        entity.setEmail(domain.getEmail());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setRole(domain.getRole());
        entity.setActive(domain.isActive());

        return entity;
    }

    public static void updateEntity(User domain, UserEntity entity) {
        if (domain == null || entity == null) return;

        entity.setEmail(domain.getEmail());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setRole(domain.getRole());
        entity.setActive(domain.isActive());
    }
}