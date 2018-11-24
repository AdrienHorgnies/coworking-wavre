package com.ifosup.coworking.domain;

import com.ifosup.coworking.domain.enumeration.SpaceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Space.
 */
@Entity
@Table(name = "space")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Space implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SpaceType type;

    @NotNull
    @Min(value = 1)
    @Column(name = "people_capacity", nullable = false)
    private Integer peopleCapacity;

    @NotNull
    @Min(value = 0)
    @Column(name = "area", nullable = false)
    private Integer area;

    @ManyToOne(optional = false)
    @NotNull
    private Building building;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "space_equipment_type",
        joinColumns = @JoinColumn(name = "spaces_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_types_id", referencedColumnName = "id"))
    private Set<EquipmentType> equipmentTypes = new HashSet<>();

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

    public Space name(String name) {
        this.name = name;
        return this;
    }

    public SpaceType getType() {
        return type;
    }

    public void setType(SpaceType type) {
        this.type = type;
    }

    public Space type(SpaceType type) {
        this.type = type;
        return this;
    }

    public Integer getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(Integer peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public Space peopleCapacity(Integer peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
        return this;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Space area(Integer area) {
        this.area = area;
        return this;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Space building(Building building) {
        this.building = building;
        return this;
    }

    public Set<EquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }

    public void setEquipmentTypes(Set<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }

    public Space equipmentTypes(Set<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
        return this;
    }

    public Space addEquipmentType(EquipmentType equipmentType) {
        this.equipmentTypes.add(equipmentType);
        equipmentType.getSpaces().add(this);
        return this;
    }

    public Space removeEquipmentType(EquipmentType equipmentType) {
        this.equipmentTypes.remove(equipmentType);
        equipmentType.getSpaces().remove(this);
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
        Space space = (Space) o;
        if (space.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), space.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Space{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", peopleCapacity='" + getPeopleCapacity() + "'" +
            ", area='" + getArea() + "'" +
            "}";
    }
}
