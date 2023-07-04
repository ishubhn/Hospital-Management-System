package io.management.hospital.entities.dto.response;

import io.management.hospital.entities.AddressEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {
    private String hospitalId;
    private String name;
    private AddressEntity address;
    private String contactNumber;
    private String alternateContactNumber;
    private String emailId;
}
