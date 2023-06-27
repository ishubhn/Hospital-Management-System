package io.service.auth.services;

import io.service.auth.entities.User;
import io.service.auth.entities.dto.LoginRequest;
import io.service.auth.entities.dto.LoginResponse;
import io.service.auth.entities.dto.RegisterRequest;
import io.service.auth.exception.UserAlreadyExistException;

public interface AuthService {
    User registerUser(RegisterRequest request) throws UserAlreadyExistException;

    LoginResponse loginUser(LoginRequest request);
}
