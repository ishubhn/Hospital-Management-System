package io.management.hospital.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String addressId;

    @NonNull
    private String address;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    @Pattern(regexp = "^\\d{6}$")
    private String pinCode;

    private String country;

    public AddressEntity(String address, String city, String state, String pinCode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
