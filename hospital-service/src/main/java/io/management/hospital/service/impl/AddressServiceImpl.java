package io.management.hospital.service.impl;

import io.management.hospital.entities.dto.mapper.HospitalMapper;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.repositories.HospitalEntityRepository;
import io.management.hospital.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl	implements AddressService {

	@Autowired
	private HospitalEntityRepository repo;

	@Override
	public List<HospitalResponse> getHospitalsByPincode(String pincode) {
		return repo.findByPincode(pincode)
				.stream()
				.map(HospitalMapper::toHospitalResponse)
				.collect(Collectors.toList());
	}
}