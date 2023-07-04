package io.management.hospital.service;

import io.management.hospital.entities.dto.request.HospitalRequest;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.exception.HospitalAlreadyPresentException;

import java.util.List;

public interface HospitalService {
    List<HospitalResponse> getAllHospitals();

    HospitalResponse getHospitalByEmailId(String emailId);

    MessageResponse createHospital(HospitalRequest hospital) throws HospitalAlreadyPresentException;

    MessageResponse deleteHospitalByEmailId(String emailId);
}
