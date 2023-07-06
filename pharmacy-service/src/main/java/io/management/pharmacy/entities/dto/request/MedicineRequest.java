package io.management.pharmacy.entities.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class MedicineRequest {
    private String name;

    private String companyName;

    private Map<String, String> medicineContent;

    private String medicineType;

    private int quantityInStock;

    private int stripSize;

    private String imageFilePath;

    private boolean inStock;

    public MedicineRequest(String name, String companyName, Map<String, String> medicineContent, int quantityInStock,
                           String medicineType, int stripSize, String imageFilePath) {
        this.name = name;
        this.companyName = companyName;
        this.medicineContent = medicineContent;
        this.quantityInStock = quantityInStock;
        this.medicineType = medicineType;
        this.stripSize = stripSize;
        this.imageFilePath = imageFilePath;
        this.inStock = false;
    }
}
