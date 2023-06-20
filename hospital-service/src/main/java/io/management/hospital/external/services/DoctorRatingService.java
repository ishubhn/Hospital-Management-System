package io.management.hospital.external.services;

import io.management.hospital.external.dto.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FEEDBACK-SERVICE")
public interface DoctorRatingService {
	@GetMapping("/ratings/doctor/{doctorId}")
	List<Ratings> getAllRatingsForDoctor(@PathVariable String doctorId);
}
