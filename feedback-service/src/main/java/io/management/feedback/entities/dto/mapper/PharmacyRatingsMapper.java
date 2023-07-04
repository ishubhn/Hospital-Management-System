package io.management.feedback.entities.dto.mapper;

import io.management.feedback.entities.PharmacyRatingEntity;
import io.management.feedback.entities.dto.request.PharmacyEntityRequest;
import io.management.feedback.entities.dto.response.PharmacyRatingsResponse;

public class PharmacyRatingsMapper {

    private PharmacyRatingsMapper() {
    }

    public static PharmacyRatingEntity toPharmacyRatingsEntity(PharmacyEntityRequest request) {
        return new PharmacyRatingEntity(request.getMedicineId(), request.getUserId(), request.getRatings(),
                request.getFeedback());
    }

    public static PharmacyRatingEntity toPharmacyRatingsEntity(PharmacyRatingsResponse response) {
        return new PharmacyRatingEntity(response.getRatingsId(), response.getMedicineId(), response.getUserId(),
                response.getRatings(), response.getFeedback());
    }

    public static PharmacyRatingsResponse toPharmacyRatingResponse(PharmacyRatingEntity entity) {
        return new PharmacyRatingsResponse(entity.getRatingsId(), entity.getMedicineId(), entity.getUserId(),
                entity.getRatings(), entity.getFeedback());
    }
}
