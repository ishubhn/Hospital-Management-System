package io.management.patient.entity.external.services;

import io.management.patient.entity.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTHENTICATION-SERVICE")
public interface UserService {
    @GetMapping("/v1/auth/user/{emailId}")
    User getUserFromUsername(@RequestHeader("Authorization") String token,
                             @PathVariable String emailId);
}
