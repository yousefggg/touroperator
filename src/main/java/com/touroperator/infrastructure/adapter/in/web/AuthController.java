package com.touroperator.infrastructure.adapter.in.web;

import com.touroperator.application.dto.request.RegisterRequest;
import com.touroperator.application.dto.response.RegisterResponse;
import com.touroperator.domain.port.in.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {

        RegisterResponse response = authUseCase.register(request);

        return ResponseEntity.ok(response);
    }

    // TODO: Полный login-эндпоинт с JWT реализовывать в Модуле 6
}
