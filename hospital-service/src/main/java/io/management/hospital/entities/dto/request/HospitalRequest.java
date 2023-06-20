package io.management.hospital.entities.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HospitalRequest {
	private String name;
	private AddressRequest address;
	private String contactNumber;
	private String alternateContactNumber;
	private String emailId;
	private String password;
}
