package com.ifosup.coworking.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;

    @Column(length = 60)
    public String password;

    @Column(length = 100)
    public String lastName;

    @Column(length = 100)
    public String firstName;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", roles=" + roles +
            '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
