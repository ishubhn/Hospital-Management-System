package io.service.auth.controllers;

import io.service.auth.entities.User;
import io.service.auth.entities.dto.LoginRequest;
import io.service.auth.entities.dto.LoginResponse;
import io.service.auth.entities.dto.RegisterRequest;
import io.service.auth.entities.dto.UserResponse;
import io.service.auth.exception.InvalidTokenException;
import io.service.auth.exception.UserAlreadyExistException;
import io.service.auth.helper.JwtUtil;
import io.service.auth.services.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) throws UserAlreadyExistException {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword())
        );
        return ResponseEntity.ok(userService.loginUser(request));
    }

    @GetMapping("/user/{emailId}")
    public ResponseEntity<UserResponse> getUserFromUsername(@RequestHeader("Authorization") String token,
                                                            @PathVariable String emailId) throws Exception {
        log.info("Inside getUserFromUserName");
        User user = userService.getUserFromUsername(emailId);

        if (jwtUtil.validateToken(token.substring(7), user)) {
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(),
                    user.getRoles(), user.getEmailId(), user.getContactNo()));
        } else {
            throw new InvalidTokenException("Invalid token provided in header");
        }
    }
}
