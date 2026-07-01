package com.touroperator.domain.port.in;

import com.touroperator.application.dto.request.LoginRequest;
import com.touroperator.application.dto.request.RegisterRequest;
import com.touroperator.application.dto.response.AuthResponse;
import com.touroperator.application.dto.response.RegisterResponse;

public interface AuthUseCase {

    RegisterResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);

}
