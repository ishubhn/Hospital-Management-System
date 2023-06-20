package io.management.hospital.entities.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponse {
	private String ownerId;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String emailId;
	private Set<String> hospitalOwnedId;
}
