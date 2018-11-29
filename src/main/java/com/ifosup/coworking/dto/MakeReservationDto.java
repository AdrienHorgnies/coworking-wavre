package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.Space;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

public class MakeReservationDto {

    @Size(min = 3, max = 25)
    private String title;

    @NotNull
    private Timestamp startDate;

    @NotNull
    private Timestamp endDate;

    @NotNull
    @Min(1)
    private Integer peopleNumber;

    @NotNull
    private Space space;

    private Set<ServiceOrderDto> serviceOrderDtos;

    private Set<EquipmentOrderDto> equipmentOrderDtos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Set<ServiceOrderDto> getServiceOrderDtos() {
        return serviceOrderDtos;
    }

    public void setServiceOrderDtos(Set<ServiceOrderDto> serviceOrderDtos) {
        this.serviceOrderDtos = serviceOrderDtos;
    }

    public Set<EquipmentOrderDto> getEquipmentOrderDtos() {
        return equipmentOrderDtos;
    }

    public void setEquipmentOrderDtos(Set<EquipmentOrderDto> equipmentOrderDtos) {
        this.equipmentOrderDtos = equipmentOrderDtos;
    }
}
