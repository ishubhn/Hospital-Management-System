package io.management.feedback.services;

import io.management.feedback.entities.dto.request.PharmacyEntityRequest;
import io.management.feedback.entities.dto.response.MessageResponse;
import io.management.feedback.entities.dto.response.PharmacyRatingsResponse;

import java.util.List;

public interface MedicineRatingService {
	List<PharmacyRatingsResponse> getAllRatingsForMedicine(String pharmacyId);
	List<PharmacyRatingsResponse> getAllMedicineRatingsFromUser(String userId);
	MessageResponse addRatingToMedicines(PharmacyEntityRequest request);
	MessageResponse updateRatingsForMedicine(int ratings, String ratingId, String userId);
	MessageResponse updateFeedbackForMedicine(String feedback, String ratingId, String userId);
	MessageResponse deleteRatingsForMedicine(String ratingId);
	int countRatingFromUserForMedicine(String medicineId, String userId);

	PharmacyRatingsResponse getRatingByRatingId(String ratingId);
}
