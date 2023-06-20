package io.management.hospital.entities.dto.mapper;

import io.management.hospital.entities.OwnerEntity;
import io.management.hospital.entities.dto.request.OwnerRequest;
import io.management.hospital.entities.dto.response.OwnerResponse;

public class OwnerMapper {

	public static OwnerEntity toOwnerEntity(OwnerRequest request) {
		return new OwnerEntity(request.getFirstName(), request.getLastName(), request.getContactNumber(),
				request.getEmailId(), request.getHospitalOwnedId());
	}

	public static OwnerResponse toOwnerResponse(OwnerEntity entity) {
		return new OwnerResponse(entity.getOwnerId(), entity.getFirstName(), entity.getLastName(),
				entity.getContactNumber(), entity.getEmailId(), entity.getHospitalOwnedId());
	}

	private OwnerMapper() {
	}
}
