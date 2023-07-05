package io.management.patient.service.impl;

import io.management.patient.entity.external.User;
import io.management.patient.entity.external.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public User loadUserByUsername(String token , String username) throws UsernameNotFoundException {
        return userService.getUserFromUsername(token, username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
