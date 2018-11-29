package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.ServiceType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ServiceOrderDto {

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private ServiceType serviceType;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
