package io.management.feedback.controller;

import io.management.feedback.entities.dto.request.PharmacyEntityRequest;
import io.management.feedback.entities.dto.request.UpdateRatingRequest;
import io.management.feedback.entities.dto.response.MessageResponse;
import io.management.feedback.entities.dto.response.PharmacyRatingsResponse;
import io.management.feedback.services.MedicineRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings/pharmacy")
public class MedicineRatingsController {

	@Autowired
	MedicineRatingService service;

	@GetMapping("/{medicineId}")
	public ResponseEntity<List<PharmacyRatingsResponse>> getAllRatingsForMedicine(@PathVariable String medicineId) {
		return new ResponseEntity<>(service.getAllRatingsForMedicine(medicineId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PharmacyRatingsResponse>> getAllMedicineRatingsFromUser(@PathVariable String userId) {
		return new ResponseEntity<>(service.getAllMedicineRatingsFromUser(userId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MessageResponse> createNewMedicineRatings(@RequestBody PharmacyEntityRequest request) {
		return new ResponseEntity<>(service.addRatingToMedicines(request), HttpStatus.OK);
	}

	@PutMapping("/ratings")
	public ResponseEntity<MessageResponse> updateRatingsForMedicine(@RequestBody UpdateRatingRequest request) {
		return ResponseEntity.ok(service.updateRatingsForMedicine(request.getRatings(), request.getRatingId(),
				request.getUserId()));
	}

	@PutMapping("/feedback")
	public ResponseEntity<MessageResponse> updateFeedbackForMedicine(@RequestBody UpdateRatingRequest request) {
		return ResponseEntity.ok(
				service.updateFeedbackForMedicine(request.getFeedback(), request.getRatingId(), request.getUserId()));
	}

	@DeleteMapping("/{ratingId}")
	public ResponseEntity<MessageResponse> deleteRatingById(@PathVariable String ratingId) {
		return new ResponseEntity<>(service.deleteRatingsForMedicine(ratingId), HttpStatus.OK);
	}
}
