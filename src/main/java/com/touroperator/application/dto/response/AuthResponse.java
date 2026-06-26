package com.touroperator.application.dto.response;

import com.touroperator.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Long userId;
    private String email;
    private Role role;
    private String token;
}