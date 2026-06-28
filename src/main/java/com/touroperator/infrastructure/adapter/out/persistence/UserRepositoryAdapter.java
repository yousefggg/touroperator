package com.touroperator.infrastructure.adapter.out.persistence;

import com.touroperator.domain.model.User;
import com.touroperator.domain.port.out.UserRepository;
import com.touroperator.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.touroperator.infrastructure.adapter.out.persistence.jpa.UserJpaRepository;
import com.touroperator.infrastructure.adapter.out.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntityForCreate(user);
        userEntity = userJpaRepository.save(userEntity);
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);
        return userEntity.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = userJpaRepository.findByEmail(email);
        return userEntity.map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
