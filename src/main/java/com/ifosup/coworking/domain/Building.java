package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Building.
 */
@Entity
@Table(name = "building")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(optional = false)
    @NotNull
    private City city;

    @OneToMany(mappedBy = "building")
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

    public Building name(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Building address(String address) {
        this.address = address;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Building city(City city) {
        this.city = city;
        return this;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<Space> spaces) {
        this.spaces = spaces;
    }

    public Building spaces(Set<Space> spaces) {
        this.spaces = spaces;
        return this;
    }

    public Building addSpace(Space space) {
        this.spaces.add(space);
        space.setBuilding(this);
        return this;
    }

    public Building removeSpace(Space space) {
        this.spaces.remove(space);
        space.setBuilding(null);
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
        Building building = (Building) o;
        if (building.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), building.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Building{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
