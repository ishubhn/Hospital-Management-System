package io.management.patient.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String contactNumber;
    private String gender;
    private LocalDate dateOfBirth;
    private AddressRequest address;
}
