package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.EquipmentPack;
import com.ifosup.coworking.domain.ServiceType;
import com.ifosup.coworking.domain.Space;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

public class MakeReservationDto {

    @Size(min = 3, max = 25)
    private String title;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @NotNull
    private Space space;

    private Set<ServiceType> serviceTypes;

    private Set<EquipmentPack> equipmentPacks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Set<EquipmentPack> getEquipmentPacks() {
        return equipmentPacks;
    }

    public void setEquipmentPacks(Set<EquipmentPack> equipmentPacks) {
        this.equipmentPacks = equipmentPacks;
    }
}
