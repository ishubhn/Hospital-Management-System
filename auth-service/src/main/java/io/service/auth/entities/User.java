package io.service.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "roles")
    private List<GrantedAuthority> roles;

    private String emailId;
    private String contactNo;

    public User(String firstName, String lastName, String password, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
