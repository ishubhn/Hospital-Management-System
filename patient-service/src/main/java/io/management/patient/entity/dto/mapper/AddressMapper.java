package io.management.patient.entity.dto.mapper;

import io.management.patient.entity.AddressEntity;
import io.management.patient.entity.dto.request.AddressRequest;
import io.management.patient.entity.dto.response.AddressResponse;

public class AddressMapper {

    private AddressMapper() {
    }

    public static AddressResponse toAddressResponse(AddressEntity address) {
        return new AddressResponse(address.getAddressId(), address.getAddressDetails(),
                address.getCity(), address.getState(), address.getPinCode());
    }

    public static AddressEntity toAddressEntity(AddressRequest address) {
        return new AddressEntity(address.getAddressDetails(), address.getCity(),
                address.getState(), address.getPinCode());

    }

}
