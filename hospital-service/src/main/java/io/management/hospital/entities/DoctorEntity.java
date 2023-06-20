package io.management.hospital.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {
	@Id
	@Column(nullable = false, updatable = false)
	private String doctorId;

	@NonNull
	@Size(min = 3, max = 18, message = "Name size should be at least of 2 characters")
	private String firstName;

	@NonNull
	@Size(min = 3, max = 18, message = "Name size should be at least of 2 characters")
	private String lastName;

	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String emailId;

	@NonNull
	@Pattern(regexp = "^\\d{10}$", message = "Number must be of 10 digit")
	private String contactNumber;

	@ElementCollection          // To handle collection inside JPA entity (JPA2 approach)
	private Map<String, String> educationDetails;    // Key -> Graduation, Specialization (MBBS - General, MD -> Ortho)

	@ElementCollection
	private Set<String> hospitalsEnrolledIn;

	public DoctorEntity(String firstName, String lastName, String emailId, String contactNumber, Map<String,
			String> educationDetails, Set<String> hospitalsEnrolledIn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.educationDetails = educationDetails;
		this.hospitalsEnrolledIn = hospitalsEnrolledIn;
	}
}
