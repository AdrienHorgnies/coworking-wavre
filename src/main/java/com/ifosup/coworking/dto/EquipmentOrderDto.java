package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.EquipmentType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EquipmentOrderDto {

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private EquipmentType equipmentType;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }
}
