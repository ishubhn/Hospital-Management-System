package io.management.hospital.entities.dto.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String emailId;
	private Set<String> hospitalOwnedId;
}
