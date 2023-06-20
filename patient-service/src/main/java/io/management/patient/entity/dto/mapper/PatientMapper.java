package io.management.patient.entity.dto.mapper;

import io.management.patient.entity.PatientEntity;
import io.management.patient.entity.dto.request.PatientRequest;
import io.management.patient.entity.dto.response.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public static PatientResponse toPatientResponse(PatientEntity user) {
        return new PatientResponse(user.getUserId(), user.getFirstName(), user.getLastName(),
                user.getGender(), user.getContactNumber(), user.getEmailId(),
                user.getDateOfBirth(), AddressMapper.toAddressResponse(user.getAddress()));
    }

    public static PatientEntity toPatientRequest(PatientRequest user) {
        return new PatientEntity(user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPassword(),
                user.getContactNumber(), user.getGender(), user.getDateOfBirth(),
                user.getAddress());
    }

    private PatientMapper() {
    }
}
