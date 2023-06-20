package io.management.hospital.repositories;

import io.management.hospital.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorEntityRepository extends JpaRepository<DoctorEntity, String> {
	Optional<DoctorEntity> findByEmailId(String emailId);
	List<DoctorEntity> findByFirstNameOrLastNameContaining(String firstName, String lastName);
}
