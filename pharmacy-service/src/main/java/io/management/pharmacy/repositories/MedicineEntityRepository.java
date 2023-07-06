package io.management.pharmacy.repositories;

import io.management.pharmacy.entities.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MedicineEntityRepository extends JpaRepository<MedicineEntity, String> {
    Set<MedicineEntity> findByNameContaining(String name);

    //	select medicine_content_key, medicine_entity_medicine_id from medicine_entity_medicine_content
//	where medicine_content_key = 'hajmolite'
    @Query(value = "SELECT * FROM medicine_entity_medicine_content me JOIN medicine m ON" +
            " me.medicine_entity_medicine_id=m.medicine_id WHERE me.medicine_content_key" +
            " = :composition",
            nativeQuery = true)
    List<MedicineEntity> findByMedicineContent(String composition);
}
