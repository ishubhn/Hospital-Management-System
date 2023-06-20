package io.management.hospital.entities.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
	private String address;
	private String city;
	private String state;
	private String pinCode;
}
