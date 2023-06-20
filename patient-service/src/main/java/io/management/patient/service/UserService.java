package io.management.patient.service;

import io.management.patient.entity.dto.request.PatientRequest;
import io.management.patient.entity.dto.response.MessageResponse;
import io.management.patient.entity.dto.response.PatientResponse;

import java.util.List;

public interface UserService {
    PatientResponse createUser(PatientRequest user);

    List<PatientResponse> getAllUsers();

    PatientResponse getUserByEmailId(String emailId);

    MessageResponse deleteUserById(String emailId);
}
