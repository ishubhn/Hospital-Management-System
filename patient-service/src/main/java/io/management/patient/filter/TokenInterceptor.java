package io.management.patient.filter;

import io.management.patient.exception.InvalidAuthorizationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor, Ordered {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String CODENAME = "TokenInterceptor";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Inside {}#preHandle", CODENAME);

        // Extract the token from the request headers or query parameters
        String token = extractToken(request);
        log.info(token);
        UserDetails userDetails = getUserDetailsFromToken(token);
        log.info("");
//        log.info("User Details -> {}", userDetails.toString());
        // Validate the token using jwtUtil

        boolean isValidToken = jwtUtil.validateToken(token, userDetails);
        log.info("Is valid token -> {}", isValidToken);
        if (isValidToken) {
            // Allow the request to proceed
            log.info("Valid Token");
            return true;
        }
        else {
            log.error("Invalid Token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private UserDetails getUserDetailsFromToken(String token) {
        try {
            log.info("Inside {}#getUserDetailsFromToken", CODENAME);
            String userName = jwtUtil.getUsernameFromToken(token);
            log.info("Username from token-> {}", userName);

            // Load user details by using userDetailsService
            return userDetailsService.loadUserByUsername(userName);
        } catch (Exception ex) {
            log.info(ex.getLocalizedMessage());
            log.info(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    private String extractToken(HttpServletRequest request) {
        log.info("Inside {}#extractToken", CODENAME);
        // Extract the token from header in request
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // extract the token by removing the "Bearer " prefix
            return authorizationHeader.substring(7);
        }

        // Return null or throw an exception if token is not found
        throw new InvalidAuthorizationTokenException("Invalid auth token or no token found");
    }

    @Override
    public int getOrder() {
        // to set the order of interceptor to execute before spring security
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
