package io.service.auth.services.impl;

import io.service.auth.entities.User;
import io.service.auth.entities.dto.LoginRequest;
import io.service.auth.entities.dto.LoginResponse;
import io.service.auth.entities.dto.RegisterRequest;
import io.service.auth.exception.UserAlreadyExistException;
import io.service.auth.helper.JwtUtil;
import io.service.auth.repository.UserRepository;
import io.service.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(RegisterRequest request) throws UserAlreadyExistException {
        // Check if the username is already taken
        if (userRepo.findByEmailId(request.getEmailId()) != null) {
            throw new UserAlreadyExistException("Username already exists");
        }

        // Create a new user
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailId(request.getEmailId());
        user.setContactNo(request.getContactNo());
        user.setRoles((request.getRoles()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save the users to db
        userRepo.save(user);
        return user;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepo.findByEmailId(request.getEmailId());

        // validate
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        // return token
        return new LoginResponse(jwtUtil.generateToken(user));
    }

    @Override
    public User getUserFromUsername(String emailId) {
        User user = userRepo.findByEmailId(emailId);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(String.format("User not found for login id -> %s", emailId));
        }
    }
}
