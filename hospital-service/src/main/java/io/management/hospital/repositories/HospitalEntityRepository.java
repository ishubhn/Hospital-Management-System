package io.management.hospital.repositories;

import io.management.hospital.entities.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalEntityRepository extends JpaRepository<HospitalEntity, String> {
    Optional<HospitalEntity> findByEmailId(String emailId);

    Optional<HospitalEntity> deleteByEmailId(String emailId);

    /*	@Query("SELECT h FROM HospitalEntity h JOIN AddressEntity a ON h.address = a.addressId" +
                "WHERE a.pinCode = :pinCode")
    */
    @Query(value = "select * from hospital h join address a " +
            "on h.address_address_id = a.address_id where a.pin_code=?1",
            nativeQuery = true)
    List<HospitalEntity> findByPincode(String pinCode);
}
