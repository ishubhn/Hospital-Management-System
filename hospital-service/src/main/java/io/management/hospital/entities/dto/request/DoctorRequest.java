package io.management.hospital.entities.dto.request;

import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private Map<String, String> educationDetails;
    private Set<String> hospitalsEnrolledIn;
}
