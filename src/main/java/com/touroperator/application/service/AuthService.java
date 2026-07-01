package com.touroperator.application.service;

import com.touroperator.application.dto.request.LoginRequest;
import com.touroperator.application.dto.request.RegisterRequest;
import com.touroperator.application.dto.response.AuthResponse;
import com.touroperator.application.dto.response.RegisterResponse;
import com.touroperator.domain.exception.InvalidCredentialsException;
import com.touroperator.domain.exception.UserAlreadyExistsException;
import com.touroperator.domain.model.Role;
import com.touroperator.domain.model.User;
import com.touroperator.domain.port.in.AuthUseCase;
import com.touroperator.domain.port.out.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            log.info("User with email {} already exists", registerRequest.getEmail());
            throw new UserAlreadyExistsException(
                    "User already exists with email: " + registerRequest.getEmail()
            );
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User(
                null,
                registerRequest.getEmail(),
                encodedPassword,
                Role.ROLE_USER
        );

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId());
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException(
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                null // JWT ДОБАВИТЬ ПОЗЖЕ
        );
    }
}