package io.management.patient.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String emailId;
    private LocalDate dateOfBirth;
    private AddressResponse permanentAddress;
}
