package io.management.hospital.service.impl;

import io.management.hospital.entities.AddressEntity;
import io.management.hospital.entities.HospitalEntity;
import io.management.hospital.entities.dto.mapper.HospitalMapper;
import io.management.hospital.entities.dto.request.HospitalRequest;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.exception.HospitalAlreadyPresentException;
import io.management.hospital.exception.NoSuchHospitalExistException;
import io.management.hospital.repositories.HospitalEntityRepository;
import io.management.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalEntityRepository hospitalRepo;

	private String className = "HospitalService";

	@Override
	public List<HospitalResponse> getAllHospitals() {
		log.info("Inside {}#getAllHospitals", className);
		log.info("All Hospitals returned");
		return hospitalRepo
				.findAll()
				.stream()
				.map(HospitalMapper::toHospitalResponse)
				.collect(Collectors.toList());
	}

	@Override
	public HospitalResponse getHospitalByEmailId(String emailId) {
		log.info("Inside {}#getHospitalByEmailId",className);
		return hospitalRepo
				.findByEmailId(emailId)
				.stream()
				.map(HospitalMapper::toHospitalResponse)
				.findFirst()
				.orElseThrow(() ->
						new NoSuchHospitalExistException(
								String.format("Hospital with email id '%s' not found", emailId)
						));
	}

	@Override
	public MessageResponse createHospital(HospitalRequest hospital) throws HospitalAlreadyPresentException {
		log.info("Inside {}#createHospital",className);
		if (isHospitalPresent(hospital.getEmailId())) {
			log.error("Hospital {} already present. New account creation process aborted.",hospital.getEmailId());
			throw new HospitalAlreadyPresentException(String.format("Hospital '%s' already exist", hospital.getEmailId()));
		} else {
			// User Entity
			String uuid = UUID.randomUUID().toString();
			String addressUuid = UUID.randomUUID().toString();

			HospitalEntity hospitalEntity = HospitalMapper.toHospitalEntity(hospital);
			hospitalEntity.setHospitalId(uuid);

			// Address Entity
			AddressEntity addressEntity = hospitalEntity.getAddress();
			addressEntity.setAddressId(addressUuid);
			addressEntity.setCountry("INDIA");

			hospitalRepo.save(hospitalEntity);

			log.info("");
			log.info("User {} created successfully", hospital.getEmailId());

			return new MessageResponse(String.format("Hospital %s -> %s created successfully.",
					hospitalEntity.getHospitalId(),
					hospitalEntity.getName()),
					"SUCCESS");
		}
	}

	@Override
	@Transactional
	public MessageResponse deleteHospitalByEmailId(String emailId) {
		log.info("Inside {}#deleteHospitalByEmailId",className);
		if (!isHospitalPresent(emailId)) {
			throw new NoSuchHospitalExistException(String.format("No such hospital exist -> %s", emailId));
		}

		hospitalRepo.deleteByEmailId(emailId);
		log.info("Account deleted successfully -> {}", emailId);
		return new MessageResponse(String.format("Hospital '%s' deleted successfully",emailId),"SUCCESS");
	}

	public boolean isHospitalPresent(String emailId) {
		HospitalEntity user = hospitalRepo.findByEmailId(emailId).orElse(null);
		return user != null;  //true = user is not null
	}
}
