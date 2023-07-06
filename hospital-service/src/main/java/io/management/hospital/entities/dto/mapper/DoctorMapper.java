package io.management.hospital.entities.dto.mapper;

import io.management.hospital.entities.DoctorEntity;
import io.management.hospital.entities.dto.request.DoctorRequest;
import io.management.hospital.entities.dto.response.DoctorResponse;

public class DoctorMapper {

    private DoctorMapper() {
    }

    public static DoctorEntity toDoctorEntity(DoctorRequest request) {
        return new DoctorEntity(request.getFirstName(), request.getLastName(), request.getEmailId(),
                request.getContactNumber(), request.getEducationDetails(), request.getHospitalsEnrolledIn());
    }

    public static DoctorResponse toDoctorResponse(DoctorEntity entity) {
        return new DoctorResponse(entity.getDoctorId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmailId(), entity.getContactNumber(), entity.getEducationDetails(),
                entity.getHospitalsEnrolledIn());
    }
}