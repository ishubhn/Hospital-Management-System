package io.management.pharmacy.entities.dto.mapper;

import io.management.pharmacy.entities.MedicineEntity;
import io.management.pharmacy.entities.dto.request.MedicineRequest;
import io.management.pharmacy.entities.dto.response.MedicineResponse;

public class MedicineMapper {

	private MedicineMapper() {
	}

	public static MedicineResponse toMedicineResponse(MedicineEntity medicine) {
		return new MedicineResponse(medicine.getMedicineId(), medicine.getName(), medicine.getCompanyName(),
				medicine.getMedicineContent(), medicine.getMedicineType(), medicine.getQuantityInStock(), medicine.getStripSize(),
				medicine.getImageFilePath(), medicine.isInStock());
	}

	public static MedicineEntity toMedicineEntity(MedicineRequest request) {
		return new MedicineEntity(request.getName(), request.getCompanyName(), request.getMedicineContent(),
				request.getMedicineType() , request.getQuantityInStock(), request.getStripSize(),
				request.getImageFilePath(), request.isInStock());
	}
}
