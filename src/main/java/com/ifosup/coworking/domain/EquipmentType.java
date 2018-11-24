package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A EquipmentType.
 */
@Entity
@Table(name = "equipment_type")
public class EquipmentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "equipmentTypes")
    @JsonIgnore
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

    public EquipmentType name(String name) {
        this.name = name;
        return this;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<Space> spaces) {
        this.spaces = spaces;
    }

    public EquipmentType spaces(Set<Space> spaces) {
        this.spaces = spaces;
        return this;
    }

    public EquipmentType addSpace(Space space) {
        this.spaces.add(space);
        space.getEquipmentTypes().add(this);
        return this;
    }

    public EquipmentType removeSpace(Space space) {
        this.spaces.remove(space);
        space.getEquipmentTypes().remove(this);
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
        EquipmentType equipmentType = (EquipmentType) o;
        if (equipmentType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipmentType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
