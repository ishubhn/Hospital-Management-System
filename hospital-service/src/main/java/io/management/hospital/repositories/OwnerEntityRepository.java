package io.management.hospital.repositories;

import io.management.hospital.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, String> {
    List<OwnerEntity> findAllByHospitalOwnedId(String hospitalId);
}