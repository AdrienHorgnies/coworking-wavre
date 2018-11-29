package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Min(0)
    @Column(name = "price", nullable = false)
    private float price;

    @ManyToMany(mappedBy = "serviceTypes")
    @JsonIgnore
    private Set<Building> buildings = new HashSet<>();

    @OneToMany(mappedBy = "serviceType")
    @JsonIgnore
    private Set<ServiceOrder> serviceOrders = new HashSet<>();
    
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ServiceType price(float price) {
        this.price = price;
        return this;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }

    public ServiceType buildings(Set<Building> buildings) {
        this.buildings = buildings;
        return this;
    }

    public ServiceType addBuilding(Building building) {
        this.buildings.add(building);
        building.getServiceTypes().add(this);
        return this;
    }

    public ServiceType removeBuilding(Building building) {
        this.buildings.remove(building);
        building.getServiceTypes().remove(this);
        return this;
    }

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public ServiceType serviceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
        return this;
    }

    public ServiceType addServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrders.add(serviceOrder);
        serviceOrder.setServiceType(this);
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
