package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "space_price_per_day")
    private Float spacePricePerDay;

    @Column(name = "grand_total_price")
    private Float grandTotalPrice;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "reservation")
    private Set<EquipmentOrder> equipmentOrders = new HashSet<>();

    @OneToMany(mappedBy = "reservation")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Set<EquipmentOrder> getEquipmentOrders() {
        return equipmentOrders;
    }

    public void setEquipmentOrders(Set<EquipmentOrder> equipmentOrders) {
        this.equipmentOrders = equipmentOrders;
    }

    public Reservation addEquipmentOrder(EquipmentOrder equipmentOrder) {
        this.equipmentOrders.add(equipmentOrder);
        equipmentOrder.setReservation(this);
        return this;
    }

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public Reservation addServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrders.add(serviceOrder);
        serviceOrder.setReservation(this);
        return this;
    }

    public Float getSpacePricePerDay() {
        return spacePricePerDay;
    }

    public void setSpacePricePerDay(Float spacePricePerDay) {
        this.spacePricePerDay = spacePricePerDay;
    }

    public Float getGrandTotalPrice() {
        return grandTotalPrice;
    }

    public void setGrandTotalPrice(Float grandTotalPrice) {
        this.grandTotalPrice = grandTotalPrice;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        if (reservation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
