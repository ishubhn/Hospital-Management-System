package io.management.patient.entity.external;

import java.util.Map;
import java.util.Set;

public class Doctor {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private Map<String, String> educationDetails;
    // fetch hospitals first, then add to set in the service and then return
    private Set<Hospital> hospitalsEnrolledIn;
}
