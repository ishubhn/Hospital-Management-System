package io.management.feedback.services;

import io.management.feedback.entities.dto.request.DoctorEntityRequest;
import io.management.feedback.entities.dto.response.DoctorRatingsResponse;
import io.management.feedback.entities.dto.response.MessageResponse;

import java.util.List;

public interface DoctorRatingService {
    List<DoctorRatingsResponse> getAllRatingsForDoctor(String doctorId);

    List<DoctorRatingsResponse> getAllDoctorsRatingsFromUser(String userId);

    MessageResponse addRatingToDoctor(DoctorEntityRequest request);

    MessageResponse updateRatingsForDoctor(int ratings, String ratingId, String userId);

    MessageResponse updateFeedbackForDoctor(String feedback, String ratingId, String userId);

    MessageResponse deleteRatingsForDoctor(String ratingId);

    int countRatingFromUserForDoctor(String doctorId, String userId);

    DoctorRatingsResponse getRatingByRatingId(String ratingId);
}
