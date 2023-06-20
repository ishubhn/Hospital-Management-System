package io.management.hospital.controller;

import io.management.hospital.entities.dto.request.HospitalRequest;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.exception.HospitalAlreadyPresentException;
import io.management.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@GetMapping("/search/all")
	public ResponseEntity<List<HospitalResponse>> getAllHospitals() {
		return ResponseEntity.ok(hospitalService.getAllHospitals());
	}

	@GetMapping("/search/{emailId}")
	public ResponseEntity<HospitalResponse> getHospitalByEmailId(@PathVariable String emailId) {
		return ResponseEntity.ok(hospitalService.getHospitalByEmailId(emailId));
	}

	@PostMapping("/add")
	public ResponseEntity<MessageResponse> createHospital(@RequestBody HospitalRequest request)
			throws HospitalAlreadyPresentException {
		return ResponseEntity.ok(hospitalService.createHospital(request));
	}

	@DeleteMapping("/delete/{emailId}")
	public ResponseEntity<MessageResponse> deleteHospitalByEmailId(@PathVariable String emailId) {
		return ResponseEntity.ok(hospitalService.deleteHospitalByEmailId(emailId));
	}
}
