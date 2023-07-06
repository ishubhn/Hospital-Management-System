package io.management.hospital.controller;

import io.management.hospital.entities.dto.request.OwnerRequest;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.entities.dto.response.OwnerResponse;
import io.management.hospital.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/owner")
public class OwnerController {
    @Autowired
    private OwnerService service;

    @GetMapping("/all/{hospitalId}")
    public ResponseEntity<List<OwnerResponse>> getAllOwnersById(@PathVariable String hospitalId) {
        return ResponseEntity.ok(service.getAllOwnersByHospitalId(hospitalId));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable String id) {
        return ResponseEntity.ok(service.getOwnerById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addOwner(@RequestBody OwnerRequest request) {
        return ResponseEntity.ok(service.addOwner(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteOwner(@PathVariable String id) {
        return ResponseEntity.ok(service.removeOwnerById(id));
    }
}
