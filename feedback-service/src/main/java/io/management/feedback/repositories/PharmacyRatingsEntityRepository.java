package io.management.feedback.repositories;

import io.management.feedback.entities.PharmacyRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRatingsEntityRepository extends JpaRepository<PharmacyRatingEntity, String> {
    List<PharmacyRatingEntity> findByMedicineId(String medicineId);

    List<PharmacyRatingEntity> findByUserId(String userId);

    @Query(value = "SELECT COUNT(*) FROM pharmacy_ratings WHERE medicine_id= ?1 AND user_id= ?2",
            nativeQuery = true)
    int getRatingsCountFromUserForMedicine(String medicineId, String userId);
}
