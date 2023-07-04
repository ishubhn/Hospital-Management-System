package io.management.pharmacy.services;

import io.management.pharmacy.entities.dto.request.MedicineRequest;
import io.management.pharmacy.entities.dto.response.MedicineResponse;
import io.management.pharmacy.entities.dto.response.MessageResponse;
import io.management.pharmacy.exceptions.NoSuchMedicineExistException;

import java.util.List;
import java.util.Set;

public interface MedicineService {
    MessageResponse addMedicinesInStock(MedicineRequest medicine);

    List<MedicineResponse> getAllMedicines();

    Set<MedicineResponse> getMedicineLikeName(String name);

    MedicineResponse getMedicineById(String medicineId);

    List<MedicineResponse> getMedicineByComposition(String composition);

    MedicineResponse deactivateMedicines(String medicineId) throws NoSuchMedicineExistException;
}
