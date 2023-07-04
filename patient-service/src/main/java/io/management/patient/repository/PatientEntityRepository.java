package io.management.patient.repository;

import io.management.patient.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientEntityRepository extends JpaRepository<PatientEntity, String> {
    Optional<PatientEntity> findByEmailId(String emailId);

    Optional<PatientEntity> deleteByEmailId(String emailId);
}