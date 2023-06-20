package io.management.hospital.service;

import io.management.hospital.entities.dto.request.OwnerRequest;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.entities.dto.response.OwnerResponse;

import java.util.List;

public interface OwnerService {
	MessageResponse addOwner(OwnerRequest request) throws NullPointerException;
	List<OwnerResponse> getAllOwnersByHospitalId(String hospitalId);
	OwnerResponse getOwnerById(String id);
	MessageResponse assignHospitalToOwner(String ownerId, String hospitalId) throws NullPointerException;
	MessageResponse removeOwnerById(String id);
}
