package io.management.pharmacy.external.services;

import io.management.pharmacy.external.dto.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FEEDBACK-SERVICE")
public interface MedicineRatingService {
	@GetMapping("/ratings/pharmacy/{medicineId}")
	List<Ratings> getAllRatingsForMedicine(@PathVariable String medicineId);
}
