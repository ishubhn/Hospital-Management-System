package io.management.pharmacy.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEDICINE")
public class MedicineEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String medicineId;

    @NonNull
    private String name;

    @NonNull
    private String companyName;

    @NonNull
    @ElementCollection
    private Map<String, String> medicineContent;

    private String medicineType;    // Capsule, Tablet, Syrup

    @Size(min = 0)
    private int quantityInStock;

    private int stripSize;

    private String imageFilePath;

    private boolean isInStock;

    public MedicineEntity(@NonNull String name, @NonNull String companyName,
                          @NonNull Map<String, String> medicineContent,
                          String medicineType, int quantityInStock, int stripSize,
                          String imageFilePath, boolean isInStock) {
        this.name = name;
        this.companyName = companyName;
        this.medicineContent = medicineContent;
        this.medicineType = medicineType;
        this.quantityInStock = quantityInStock;
        this.stripSize = stripSize;
        this.imageFilePath = imageFilePath;
        this.isInStock = isInStock;
    }
}
