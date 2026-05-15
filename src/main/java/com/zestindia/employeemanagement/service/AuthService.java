package com.zestindia.employeemanagement.service;

import com.zestindia.employeemanagement.dto.AuthResponse;
import com.zestindia.employeemanagement.dto.LoginRequest;
import com.zestindia.employeemanagement.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
