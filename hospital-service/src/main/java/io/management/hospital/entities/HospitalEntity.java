package io.management.hospital.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOSPITAL")
public class HospitalEntity {
	@Id
	@Column(nullable = false, updatable = false)
	private String hospitalId;

	@NonNull
	@Size(min = 3, max = 18, message = "Name size should be at least of 2 characters")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)     //  This very important, else throws entityNotFoundException
	@JoinColumn(name = "address_address_id")
	private AddressEntity address;

	@NonNull
	@Pattern(regexp = "^\\d{10}$", message = "Number must be of 10 digit")
	private String contactNumber;

	@Pattern(regexp = "^\\d{10}$", message = "Number must be of 10 digit")
	private String alternateContactNumber;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String emailId;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
			message = "Password must contain at least 1 digit, 1 lowercase, 1 uppercase and" +
					"1 symbol characters")
	private String password;

	public HospitalEntity(String name, AddressEntity address, String contactNumber, String alternateContactNumber,
	                      String emailId, String password) {
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.alternateContactNumber = alternateContactNumber;
		this.emailId = emailId;
		this.password = password;
	}
}