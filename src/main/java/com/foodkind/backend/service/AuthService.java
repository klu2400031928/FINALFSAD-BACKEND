package com.foodkind.backend.service;

import com.foodkind.backend.dto.JwtResponse;
import com.foodkind.backend.dto.LoginRequest;
import com.foodkind.backend.dto.RegisterRequest;

public interface AuthService {

    JwtResponse login(LoginRequest request);

    JwtResponse register(RegisterRequest request);
}
