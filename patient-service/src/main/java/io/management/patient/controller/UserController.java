package io.management.patient.controller;

import io.management.patient.entity.dto.request.PatientRequest;
import io.management.patient.entity.dto.response.MessageResponse;
import io.management.patient.entity.dto.response.PatientResponse;
import io.management.patient.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/search/all")
    public ResponseEntity<List<PatientResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/search/{emailId}")
    public ResponseEntity<PatientResponse> getUserByEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(userService.getUserByEmailId(emailId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PatientResponse> addUser(@RequestBody PatientRequest user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/search/{emailId}")
    public ResponseEntity<MessageResponse> deleteUserByEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(userService.deleteUserById(emailId), HttpStatus.OK);
    }

}