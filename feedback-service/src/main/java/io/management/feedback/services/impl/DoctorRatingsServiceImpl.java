package io.management.feedback.services.impl;

import io.management.feedback.entities.DoctorRatingsEntity;
import io.management.feedback.entities.dto.Status;
import io.management.feedback.entities.dto.mapper.DoctorRatingsMapper;
import io.management.feedback.entities.dto.request.DoctorEntityRequest;
import io.management.feedback.entities.dto.response.DoctorRatingsResponse;
import io.management.feedback.entities.dto.response.MessageResponse;
import io.management.feedback.exception.NoSuchRatingException;
import io.management.feedback.exception.RatingAlreadyExistException;
import io.management.feedback.repositories.DoctorRatingsEntityRepository;
import io.management.feedback.services.DoctorRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorRatingsServiceImpl implements DoctorRatingService {

    private static final String CLASSNAME = "DoctorRatingService";
    @Autowired
    private DoctorRatingsEntityRepository doctorRatingsRepo;

    @Override
    public List<DoctorRatingsResponse> getAllRatingsForDoctor(String doctorId) {
        log.info("Inside {}#getAllRatingsOfDoctor", CLASSNAME);

        return doctorRatingsRepo
                .findByDoctorId(doctorId)
                .stream()
                .map(DoctorRatingsMapper::toDoctorRatingsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorRatingsResponse> getAllDoctorsRatingsFromUser(String userId) throws NoSuchRatingException {
        log.info("Inside {}#getAllDoctorsRatingsFromUser", CLASSNAME);
        return doctorRatingsRepo.findByUserId(userId)
                .stream()
                .map(DoctorRatingsMapper::toDoctorRatingsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse addRatingToDoctor(DoctorEntityRequest request) {
        log.info("Inside {}#addRatingToDoctor", CLASSNAME);

        if (countRatingFromUserForDoctor(request.getDoctorId(), request.getUserId()) != 1) {
            // User Entity
            String ratingId = UUID.randomUUID().toString();

            DoctorRatingsEntity entity = DoctorRatingsMapper.toDoctorRatingsEntity(request);
            entity.setRatingId(ratingId);

            doctorRatingsRepo.save(entity);
            log.info("User {} created successfully", entity.getRatingId());

            return new MessageResponse("Rating added successfully", String.valueOf(Status.SUCCESS));
        } else {
            log.error("Rating already exist by user {} for doctor {}", request.getDoctorId(), request.getUserId());
            throw new RatingAlreadyExistException(String.format("Rating already exist by user '%s' for doctor '%s",
                    request.getDoctorId(), request.getUserId()));
        }
    }

    @Override
    public MessageResponse updateRatingsForDoctor(int ratings, String ratingId, String userId) {
        log.info("Inside {}#updateRatingsForDoctor", CLASSNAME);
        DoctorRatingsResponse ratingEntity = getRatingByRatingId(ratingId);
        MessageResponse message;

        if (ratingEntity.getUserId().equalsIgnoreCase(userId)) {
            if (ratings >= 1 && ratings <= 5) {
                ratingEntity.setRatings(ratings);
                log.info("user id matched");
                doctorRatingsRepo.save(DoctorRatingsMapper.toDoctorRatingsEntity(ratingEntity));

                log.info("Rating is updated successfully for rating id -> {}", ratingId);
                message = new MessageResponse(String.format("Rating is updated successfully for rating id -> %s",
                        ratingId), String.valueOf(Status.SUCCESS));
            } else {
                log.error("Unable to update ratings -> {} for ratingId -> {} as rating value is not between 1 to 5",
                        ratings, ratingId);
                message = new MessageResponse(
                        String.format("Unable to update ratings -> '%s' for ratingId -> '%s' as rating value " +
                                        "is not between 1 to 5",
                                ratings, ratingId),
                        String.valueOf(Status.ERROR));
            }
        } else {
            message = new MessageResponse(
                    String.format("User id mismatch for rating. Expected user id -> '%s'; Actual user id -> '%s'"
                            , ratingEntity.getUserId(), userId),
                    String.valueOf(Status.ERROR)
            );
        }

        return message;
    }

    @Override
    public MessageResponse updateFeedbackForDoctor(String feedback, String ratingId, String userId) {
        log.info("Inside {}#updateFeedbackForDoctor", CLASSNAME);
        DoctorRatingsResponse ratingEntity = getRatingByRatingId(ratingId);
        MessageResponse message;

        if (ratingEntity.getUserId().equalsIgnoreCase(userId)) {
            if (feedback.length() <= 300) {
                ratingEntity.setFeedback(feedback);

                doctorRatingsRepo.save(DoctorRatingsMapper.toDoctorRatingsEntity(ratingEntity));

                log.info("Feedback is updated successfully for rating id -> {}", ratingId);
                message = new MessageResponse(String.format("Feedback is updated successfully for rating id -> %s",
                        ratingId), String.valueOf(Status.SUCCESS));
            } else {
                message = new MessageResponse(String.format("An error occurred while updating feedback successfully for " +
                                "rating id -> %s. Please keep length of the feedback below 300 characters",
                        ratingId), String.valueOf(Status.ERROR));
            }

        } else {
            message = new MessageResponse(
                    String.format("User id mismatch for rating. Expected user id -> '%s'; Actual user id -> '%s'"
                            , ratingEntity.getUserId(), userId),
                    String.valueOf(Status.ERROR)
            );
        }
        return message;
    }

    @Override
    public MessageResponse deleteRatingsForDoctor(String ratingId) {
        log.info("Inside {}#deleteRatingsForDoctor", CLASSNAME);
//		 add condition if exists
        doctorRatingsRepo.deleteById(ratingId);
        return new MessageResponse(String.format("Rating deleted successfully for '%s'", ratingId),
                String.valueOf(Status.SUCCESS));
    }

    @Override
    public int countRatingFromUserForDoctor(String doctorId, String userId) {
        log.info("Inside {}#countRatingFromUserForDoctor", CLASSNAME);
        return doctorRatingsRepo.getRatingsCountFromUserForDoctor(doctorId, userId);
    }

    @Override
    public DoctorRatingsResponse getRatingByRatingId(String ratingId) {
        log.info("Inside {}#getRatingByRatingId", CLASSNAME);
        return doctorRatingsRepo
                .findById(ratingId)
                .stream()
                .map(DoctorRatingsMapper::toDoctorRatingsResponse)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchRatingException(
                                (String.format("No rating found for rating id -> '%s'", ratingId)))
                );
    }
}
