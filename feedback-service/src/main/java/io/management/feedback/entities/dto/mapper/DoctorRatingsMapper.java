package io.management.feedback.entities.dto.mapper;

import io.management.feedback.entities.DoctorRatingsEntity;
import io.management.feedback.entities.dto.request.DoctorEntityRequest;
import io.management.feedback.entities.dto.response.DoctorRatingsResponse;

public class DoctorRatingsMapper {

    private DoctorRatingsMapper() {
    }

    public static DoctorRatingsEntity toDoctorRatingsEntity(DoctorEntityRequest request) {
        return new DoctorRatingsEntity(request.getDoctorId(), request.getUserId(), request.getRatings(), request.getFeedback());
    }

    public static DoctorRatingsEntity toDoctorRatingsEntity(DoctorRatingsResponse response) {
        return new DoctorRatingsEntity(response.getRatingId(), response.getDoctorId(),
                response.getUserId(), response.getRatings(), response.getFeedback());
    }

    public static DoctorRatingsResponse toDoctorRatingsResponse(DoctorRatingsEntity entity) {
        return new DoctorRatingsResponse(entity.getRatingId(), entity.getDoctorId(), entity.getUserId(), entity.getRatings(),
                entity.getFeedback());
    }
}
