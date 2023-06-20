package io.management.patient.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private static final String COUNTRY = "India";
    private String addressId;
    private String addressDetails;
    private String city;
    private String state;
    private String pinCode;
}
