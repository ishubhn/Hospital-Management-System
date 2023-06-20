package io.management.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String addressId;

    @NonNull
    private String addressDetails;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    @Pattern(regexp = "^\\d{6}$")
    private String pinCode;

    private static final String COUNTRY = "India";

    public AddressEntity(String addressDetails, String city, String state, String pinCode) {
        this.addressDetails = addressDetails;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
