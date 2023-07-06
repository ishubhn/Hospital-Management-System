package io.management.feedback.repositories;

import io.management.feedback.entities.DoctorRatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRatingsEntityRepository extends JpaRepository<DoctorRatingsEntity, String> {
    List<DoctorRatingsEntity> findByDoctorId(String doctorId);

    List<DoctorRatingsEntity> findByUserId(String userId);

    @Query(value = "SELECT COUNT(*) FROM doctor_ratings WHERE doctor_id= ?1 AND user_id= ?2",
            nativeQuery = true)
    int getRatingsCountFromUserForDoctor(String doctorId, String userId);


}
