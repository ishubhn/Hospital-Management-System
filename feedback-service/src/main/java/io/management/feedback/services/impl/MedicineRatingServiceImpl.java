package io.management.feedback.services.impl;

import io.management.feedback.entities.PharmacyRatingEntity;
import io.management.feedback.entities.dto.Status;
import io.management.feedback.entities.dto.mapper.PharmacyRatingsMapper;
import io.management.feedback.entities.dto.request.PharmacyEntityRequest;
import io.management.feedback.entities.dto.response.MessageResponse;
import io.management.feedback.entities.dto.response.PharmacyRatingsResponse;
import io.management.feedback.exception.NoSuchRatingException;
import io.management.feedback.exception.RatingAlreadyExistException;
import io.management.feedback.repositories.PharmacyRatingsEntityRepository;
import io.management.feedback.services.MedicineRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicineRatingServiceImpl implements MedicineRatingService {

	@Autowired
	private PharmacyRatingsEntityRepository pharmacyRatingsRepo;

	public static final String CLASSNAME = "MedicineRatingServiceImpl";

	@Override
	public List<PharmacyRatingsResponse> getAllRatingsForMedicine(String medicineId) {
		log.info("Inside {}#getAllRatingsForMedicine", CLASSNAME);
		return pharmacyRatingsRepo
				.findByMedicineId(medicineId)
				.stream()
				.map(PharmacyRatingsMapper::toPharmacyRatingResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PharmacyRatingsResponse> getAllMedicineRatingsFromUser(String userId) {
		log.info("Inside {}#getAllMedicineRatingsFromUser", CLASSNAME);

		return pharmacyRatingsRepo.findByUserId(userId)
				.stream()
				.map(PharmacyRatingsMapper::toPharmacyRatingResponse)
				.collect(Collectors.toList());
	}

	@Override
	public MessageResponse addRatingToMedicines(PharmacyEntityRequest request) {
		log.info("Inside {}#addRatingToMedicines", CLASSNAME);

		if (countRatingFromUserForMedicine(request.getMedicineId(), request.getUserId()) != 1) {
			// User Entity
			String ratingId = UUID.randomUUID().toString();

			PharmacyRatingEntity entity = PharmacyRatingsMapper.toPharmacyRatingsEntity(request);

			entity.setRatingsId(ratingId);

			pharmacyRatingsRepo.save(entity);
			log.info("User {} created successfully", entity.getRatingsId());

			return new MessageResponse("Rating added successfully", String.valueOf(Status.SUCCESS));
		}
		else {
			log.error("Rating already exist by user {} for medicine {}", request.getMedicineId(), request.getUserId());
			throw new RatingAlreadyExistException(String.format("Rating already exist by user '%s' for medicine '%s",
					request.getMedicineId(), request.getUserId()));
		}
	}

	@Override
	public MessageResponse updateRatingsForMedicine(int ratings, String ratingId, String userId) {
		log.info("Inside {}#updateRatingsForMedicine", CLASSNAME);
		PharmacyRatingsResponse ratingEntity = getRatingByRatingId(ratingId);
		MessageResponse message;

		if (ratingEntity.getUserId().equalsIgnoreCase(userId)) {
			if (ratings >=1 && ratings <=5) {
				ratingEntity.setRatings(ratings);

				pharmacyRatingsRepo.save(PharmacyRatingsMapper.toPharmacyRatingsEntity(ratingEntity));

				log.info("Rating is updated successfully for rating id -> {}", ratingId);
				message = new MessageResponse(String.format("Rating is updated successfully for rating id -> %s",
						ratingId), String.valueOf(Status.SUCCESS));
			}
			else {
				log.error("Unable to update ratings -> {} for ratingId -> {} as rating value is not between 1 to 5",
						ratings, ratingId);
				message = new MessageResponse(
						String.format("Unable to update ratings -> '%s' for ratingId -> '%s' as rating value " +
										"is not between 1 to 5",
								ratings, ratingId),
						String.valueOf(Status.ERROR));
			}
		}
		else {
			message = new MessageResponse(
					String.format("User id mismatch for rating. Expected user id -> '%s'; Actual user id -> '%s'"
							, ratingEntity.getUserId(), userId),
					String.valueOf(Status.ERROR)
			);
		}

		return message;
	}

	@Override
	public MessageResponse updateFeedbackForMedicine(String feedback, String ratingId, String userId) {
		log.info("Inside {}#updateRatingsForMedicine", CLASSNAME);
		PharmacyRatingsResponse ratingEntity = getRatingByRatingId(ratingId);
		MessageResponse message;

		if (ratingEntity.getUserId().equalsIgnoreCase(userId)) {
			if (feedback.length() < 300) {
				ratingEntity.setFeedback(feedback);
				log.info("user matched.");
				pharmacyRatingsRepo.save(PharmacyRatingsMapper.toPharmacyRatingsEntity(ratingEntity));

				log.info("Feedback is updated successfully for rating id -> {}", ratingId);
				message = new MessageResponse(String.format("Feedback is updated successfully for rating id -> %s",
						ratingId), String.valueOf(Status.SUCCESS));
			}
			else {
				message = new MessageResponse(String.format("An error occurred while updating feedback successfully for " +
								"rating id -> %s. Please keep length of the feedback below 300 characters",
						ratingId), String.valueOf(Status.ERROR));
			}
		}
		else {
			message = new MessageResponse(
					String.format("User id mismatch for rating. Expected user id -> '%s'; Actual user id -> '%s'"
							, ratingEntity.getUserId(), userId),
					String.valueOf(Status.ERROR)
					);
		}


		return message;
	}

	@Override
	public MessageResponse deleteRatingsForMedicine(String ratingId) {
		log.info("Inside {}#deleteRatingsForMedicine", CLASSNAME);
		// add validation
		pharmacyRatingsRepo.deleteById(ratingId);
		return new MessageResponse(String.format("Rating deleted successfully for '%s'", ratingId),
				String.valueOf(Status.SUCCESS));
	}

	@Override
	public int countRatingFromUserForMedicine(String medicineId, String userId) {
		log.info("Inside {}#countRatingFromUserForMedicine", CLASSNAME);
		return pharmacyRatingsRepo.getRatingsCountFromUserForMedicine(medicineId, userId);
	}

	@Override
	public PharmacyRatingsResponse getRatingByRatingId(String ratingId) {
		log.info("Inside {}#getRatingByRatingId", CLASSNAME);
		return pharmacyRatingsRepo
				.findById(ratingId)
				.stream()
				.map(PharmacyRatingsMapper::toPharmacyRatingResponse)
				.findFirst()
				.orElseThrow(
						() -> new NoSuchRatingException(
								(String.format("No rating found for rating id -> '%s'", ratingId)))
				);
	}
}