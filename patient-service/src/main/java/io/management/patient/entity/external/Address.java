package io.management.patient.entity.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String addressId;
	private String address;
	private String city;
	private String state;
	private String pinCode;
	private String country;
}
