package io.management.hospital.entities.dto.mapper;

import io.management.hospital.entities.AddressEntity;
import io.management.hospital.entities.dto.request.AddressRequest;
import io.management.hospital.entities.dto.response.AddressResponse;

public class AddressMapper {
	public static AddressResponse toAddressResponse(AddressEntity address) {
		return new AddressResponse(address.getAddressId(), address.getAddress(),
				address.getCity(), address.getState(), address.getPinCode());
	}

	public static AddressEntity toAddressEntity(AddressRequest address) {
		return new AddressEntity(address.getAddress(), address.getCity(),
				address.getState(), address.getPinCode());

	}

	private AddressMapper() {
	}
}
