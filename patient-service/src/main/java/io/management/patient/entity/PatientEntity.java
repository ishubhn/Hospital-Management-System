package io.management.patient.entity;

import io.management.patient.entity.dto.mapper.AddressMapper;
import io.management.patient.entity.dto.request.AddressRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PATIENT")
public class PatientEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String contactNumber;
    private String gender;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_address_id")
    private AddressEntity address;

    public PatientEntity(String firstName, String lastName, String emailId,
                         String password, String contactNumber, String gender,
                         LocalDate dateOfBirth, AddressRequest address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = AddressMapper.toAddressEntity(address);
    }
}
