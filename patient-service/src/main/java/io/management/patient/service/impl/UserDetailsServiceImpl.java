package io.management.patient.service.impl;

import io.management.patient.entity.external.User;
import io.management.patient.entity.external.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public User loadUserByUsername(String token , String username) throws UsernameNotFoundException {
        User userDetails = userService.getUserFromUsername(token, username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDetails;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
