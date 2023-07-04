package io.management.hospital.entities.dto.mapper;

import io.management.hospital.entities.HospitalEntity;
import io.management.hospital.entities.dto.request.HospitalRequest;
import io.management.hospital.entities.dto.response.HospitalResponse;

import static io.management.hospital.entities.dto.mapper.AddressMapper.toAddressEntity;

public class HospitalMapper {

    private HospitalMapper() {
    }

    public static HospitalEntity toHospitalEntity(HospitalRequest request) {    //convert address
        // entity to address request
        return new HospitalEntity(request.getName(), toAddressEntity(request.getAddress()),
                request.getContactNumber(), request.getAlternateContactNumber(),
                request.getEmailId(), request.getPassword());
    }

    public static HospitalResponse toHospitalResponse(HospitalEntity entity) {
        return new HospitalResponse(entity.getHospitalId(), entity.getName(),
                entity.getAddress(), entity.getContactNumber(), entity.getAlternateContactNumber(),
                entity.getEmailId());
    }
}
