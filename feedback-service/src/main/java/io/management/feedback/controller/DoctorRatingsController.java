package io.management.feedback.controller;

import io.management.feedback.entities.dto.request.DoctorEntityRequest;
import io.management.feedback.entities.dto.request.UpdateRatingRequest;
import io.management.feedback.entities.dto.response.DoctorRatingsResponse;
import io.management.feedback.entities.dto.response.MessageResponse;
import io.management.feedback.services.DoctorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings/doctor")
public class DoctorRatingsController {

	@Autowired
	DoctorRatingService service;

	@GetMapping("/{doctorId}")
	public ResponseEntity<List<DoctorRatingsResponse>> getAllRatingsForDoctor(@PathVariable String doctorId) {
		return new ResponseEntity<>(service.getAllRatingsForDoctor(doctorId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<DoctorRatingsResponse>> getAllDoctorsRatingsFromUser(@PathVariable String userId) {
		return new ResponseEntity<>(service.getAllDoctorsRatingsFromUser(userId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MessageResponse> createNewDoctorRating(@RequestBody DoctorEntityRequest request) {
		return new ResponseEntity<>(service.addRatingToDoctor(request), HttpStatus.CREATED);
	}

	@PutMapping("/ratings")
	public ResponseEntity<MessageResponse> updateRatingsForDoctor(@RequestBody UpdateRatingRequest request) {
		return new ResponseEntity<>(
				service.updateRatingsForDoctor(request.getRatings(), request.getRatingId(),request.getUserId()),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("/feedback")
	public ResponseEntity<MessageResponse> updateFeedbackForDoctor(@RequestBody UpdateRatingRequest request) {
		return new ResponseEntity<>(service.updateFeedbackForDoctor(request.getFeedback(), request.getRatingId(),
				request.getUserId()), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{ratingId}")
	public ResponseEntity<MessageResponse> deleteRatingById(@PathVariable String ratingId) {
		return new ResponseEntity<>(service.deleteRatingsForDoctor(ratingId), HttpStatus.OK);
	}
}
