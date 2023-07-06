package io.management.hospital.controller;

import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/location/{pincode}")
    public ResponseEntity<List<HospitalResponse>> getHospitalsByPinCode(@PathVariable String pincode) {
        return ResponseEntity.ok(service.getHospitalsByPincode(pincode));
    }
}
