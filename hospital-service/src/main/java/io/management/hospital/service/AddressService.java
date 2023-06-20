package io.management.hospital.service;

import io.management.hospital.entities.dto.response.HospitalResponse;

import java.util.List;

public interface AddressService {
	List<HospitalResponse> getHospitalsByPincode(String pincode);
}