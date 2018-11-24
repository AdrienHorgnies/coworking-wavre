package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A ServiceType.
 */
@Entity
@Table(name = "service_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "serviceTypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Space> spaces = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceType name(String name) {
        this.name = name;
        return this;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<Space> spaces) {
        this.spaces = spaces;
    }

    public ServiceType spaces(Set<Space> spaces) {
        this.spaces = spaces;
        return this;
    }

    public ServiceType addSpace(Space space) {
        this.spaces.add(space);
        space.getServiceTypes().add(this);
        return this;
    }

    public ServiceType removeSpace(Space space) {
        this.spaces.remove(space);
        space.getServiceTypes().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceType serviceType = (ServiceType) o;
        if (serviceType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serviceType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServiceType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
