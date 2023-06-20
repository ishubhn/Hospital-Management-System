package io.management.hospital.entities.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	private String addressId;
	private String address;
	private String city;
	private String state;
	private String pinCode;
	private String country;

	public AddressResponse(String addressId, String addressDetails, String city, String state, String pinCode) {
	}
}
