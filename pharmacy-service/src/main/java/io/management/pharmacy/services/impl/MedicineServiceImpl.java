package io.management.pharmacy.services.impl;

import io.management.pharmacy.entities.MedicineEntity;
import io.management.pharmacy.entities.dto.mapper.MedicineMapper;
import io.management.pharmacy.entities.dto.request.MedicineRequest;
import io.management.pharmacy.entities.dto.response.MedicineResponse;
import io.management.pharmacy.entities.dto.response.MessageResponse;
import io.management.pharmacy.exceptions.NoSuchMedicineExistException;
import io.management.pharmacy.external.dto.Ratings;
import io.management.pharmacy.external.services.MedicineRatingService;
import io.management.pharmacy.repositories.MedicineEntityRepository;
import io.management.pharmacy.services.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.management.pharmacy.entities.dto.mapper.MedicineMapper.toMedicineEntity;
import static io.management.pharmacy.entities.dto.mapper.MedicineMapper.toMedicineResponse;

@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineEntityRepository repo;

	@Autowired
	private MedicineRatingService service;

	@Override
	public MessageResponse addMedicinesInStock(MedicineRequest medicine) {
		String message;
		String statusType = "ERROR";

		if (medicine != null) {
			String id = UUID.randomUUID().toString();
			MedicineEntity entity = toMedicineEntity(medicine);
			entity.setMedicineId(id);
			repo.save(entity);
			message = "Medicine saved successfully -> " + id;
			statusType = "SUCCESS";
		} else {
			message = "Medicine request passed in is either empty or null";
		}
		return new MessageResponse(message, statusType);
	}

	@Override
	public List<MedicineResponse> getAllMedicines() {
		List<MedicineResponse> responseList = repo.findAll()
				.stream()
				.map(MedicineMapper::toMedicineResponse)
				.collect(Collectors.toList());

		responseList.forEach(x -> x.setRatings(service.getAllRatingsForMedicine(x.getMedicineId())));

		return responseList;
	}

	@Override
	public Set<MedicineResponse> getMedicineLikeName(String name) {
		Set<MedicineResponse> responseList = repo.findByNameContaining(name)
												.stream()
												.map(MedicineMapper::toMedicineResponse)
												.collect(Collectors.toSet());

		responseList.forEach(x -> x.setRatings(service.getAllRatingsForMedicine(x.getMedicineId())));

		return responseList;
	}

	@Override
	public MedicineResponse getMedicineById(String medicineId) {
		Optional<MedicineResponse> response = repo.findById(medicineId)
				.stream()
				.map(MedicineMapper::toMedicineResponse)
				.findFirst();

		List<Ratings> ratings =  service.getAllRatingsForMedicine(response.get().getMedicineId());

		if (response.isPresent()) {
			response.get().setRatings(ratings);
			return response.get();
		}
		else {
			throw new
					NoSuchMedicineExistException(
							String.format("No Such Medicine '%s' Exist", medicineId)
			);

		}
	}

	@Override
	public List<MedicineResponse> getMedicineByComposition(String composition) {
		List<MedicineResponse> responseList = repo.findByMedicineContent(composition)
				.stream()
				.map(MedicineMapper::toMedicineResponse)
				.collect(Collectors.toList());

		responseList.forEach(x -> x.setRatings(service.getAllRatingsForMedicine(x.getMedicineId())));

		return responseList;
	}

	@Override
	public MedicineResponse deactivateMedicines(String medicineId) throws NoSuchMedicineExistException {
		if (medicineId != null) {
			Optional<MedicineEntity> entity = repo.findById(medicineId);
			if (entity.isPresent() && entity.get().isInStock()) {
				entity.get().setInStock(false);
				repo.save(entity.get());
				return toMedicineResponse(entity.get());
			}
		}

		throw new NoSuchMedicineExistException(String.format("No medicine with medicine id -> %s exist.",
					medicineId));

	}

}
