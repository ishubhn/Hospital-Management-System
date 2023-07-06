package io.management.hospital.entities.dto.response;

import io.management.hospital.external.dto.Ratings;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponse {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private List<Ratings> ratings;
    private Map<String, String> educationDetails;
    private Set<String> hospitalsEnrolledIn;
    private String about;

    public DoctorResponse(String doctorId, String firstName, String lastName, String emailId, String contactNumber,
                          Map<String, String> educationDetails, Set<String> hospitalsEnrolledIn) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.educationDetails = educationDetails;
        this.hospitalsEnrolledIn = hospitalsEnrolledIn;
    }

    public DoctorResponse(String doctorId, String about) {
        this.doctorId = doctorId;
        this.about = about;
    }
}
