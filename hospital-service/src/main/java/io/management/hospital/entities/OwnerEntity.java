package io.management.hospital.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OWNER")
public class OwnerEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String ownerId;

    @NonNull
    @Size(min = 3, max = 18, message = "Name size should be at least of 2 characters")
    private String firstName;

    @NonNull
    @Size(min = 3, max = 18, message = "Name size should be at least of 2 characters")
    private String lastName;

    @NonNull
    @Pattern(regexp = "^\\d{10}$", message = "Number must be of 10 digit")
    private String contactNumber;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String emailId;

    @ElementCollection
    private Set<String> hospitalOwnedId;

    public OwnerEntity(String firstName, String lastName, String contactNumber, String emailId,
                       Set<String> hospitalOwnedId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.hospitalOwnedId = hospitalOwnedId;
    }
}
