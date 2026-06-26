package com.touroperator.infrastructure.adapter.out.persistence.mapper;

import com.touroperator.domain.model.Role;
import com.touroperator.domain.model.User;
import com.touroperator.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    @Test
    void should_map_entity_to_domain_correctly() {

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setEmail("test@mail.com");
        entity.setPasswordHash("hash123");
        entity.setRole(Role.ROLE_USER);
        entity.setActive(true);

        User actual = UserMapper.toDomain(entity);

        assertEquals(1L, actual.getId());
        assertEquals("test@mail.com", actual.getEmail());
        assertEquals("hash123", actual.getPasswordHash());
        assertEquals(Role.ROLE_USER, actual.getRole());
        assertTrue(actual.isActive());
    }

    @Test
    void should_return_null_when_entity_is_null() {

        User actual = UserMapper.toDomain(null);

        assertNull(actual);
    }

    @Test
    void should_return_null_when_domain_is_null() {

        UserEntity actual = UserMapper.toEntityForCreate(null);

        assertNull(actual);
    }

    @Test
    void should_map_domain_to_entity_for_create() {

        User domain = new User(
                1L,
                "test@mail.com",
                "hash123",
                Role.ROLE_USER
        );

        UserEntity actual = UserMapper.toEntityForCreate(domain);

        assertEquals("test@mail.com", actual.getEmail());
        assertEquals("hash123", actual.getPasswordHash());
        assertEquals(Role.ROLE_USER, actual.getRole());
        assertTrue(actual.isActive());

        assertNull(actual.getId()); // важно: id генерируется БД
    }

    @Test
    void should_update_existing_entity_from_domain() {

        User domain = new User(
                1L,
                "new@mail.com",
                "newHash",
                Role.ROLE_ADMIN
        );

        UserEntity entity = new UserEntity();

        UserMapper.updateEntity(domain, entity);

        assertEquals("new@mail.com", entity.getEmail());
        assertEquals("newHash", entity.getPasswordHash());
        assertEquals(Role.ROLE_ADMIN, entity.getRole());
        assertTrue(entity.isActive());
    }

    @Test
    void should_not_update_when_domain_is_null() {

        UserEntity entity = new UserEntity();
        entity.setEmail("old@mail.com");
        entity.setPasswordHash("oldHash");

        UserMapper.updateEntity(null, entity);

        assertEquals("old@mail.com", entity.getEmail());
        assertEquals("oldHash", entity.getPasswordHash());
    }

    @Test
    void should_not_update_when_entity_is_null() {

        User domain = new User(
                1L,
                "test@mail.com",
                "hash123",
                Role.ROLE_USER
        );

        UserMapper.updateEntity(domain, null);

        assertTrue(true); // просто проверка, что нет исключения
    }
}