package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.*;
import com.ifosup.coworking.dto.EquipmentOrderDto;
import com.ifosup.coworking.dto.MakeReservationDto;
import com.ifosup.coworking.dto.ServiceOrderDto;
import com.ifosup.coworking.repository.EquipmentTypeRepository;
import com.ifosup.coworking.repository.ReservationRepository;
import com.ifosup.coworking.repository.ServiceTypeRepository;
import com.ifosup.coworking.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SpaceRepository spaceRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository, SpaceRepository spaceRepository, EquipmentTypeRepository equipmentTypeRepository, ServiceTypeRepository serviceTypeRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.userService = userService;
    }

    public Reservation save(MakeReservationDto makeReservationDto) {
        Timestamp now = Timestamp.from(Instant.now());

        Space trustedSpace = trustedSpace(makeReservationDto.getSpace());

        Reservation reservation = new Reservation();
        reservation.setTitle(makeReservationDto.getTitle());
        reservation.setOrderDate(now);
        reservation.setStartDate(makeReservationDto.getStartDate());
        reservation.setEndDate(makeReservationDto.getEndDate());
        reservation.setPeopleNumber(makeReservationDto.getPeopleNumber());
        reservation.setSpacePricePerDay(trustedSpace.getPrice());
        reservation.setUser(userService.getCurrentUser());
        reservation.setSpace(trustedSpace);

        for (EquipmentOrderDto equipmentOrderDto : makeReservationDto.getEquipmentOrderDtos()) {
            reservation.addEquipmentOrder(trustedEquipmentOrder(trustedSpace, equipmentOrderDto));
        }
        for (ServiceOrder serviceOrder : trustedServiceOrders(makeReservationDto.getServiceOrderDtos())) {
            reservation.addServiceOrder(serviceOrder);
        }

        int durationInDays = (int) Duration.between(reservation.getStartDate().toInstant(), reservation.getEndDate().toInstant()).toDays();
        float spaceLocationPrice = trustedSpace.getPrice() * durationInDays;
        float equipmentPrice = (float) reservation.getEquipmentOrders().stream()
            .mapToDouble(equipmentOrder -> equipmentOrder.getQuantity() * equipmentOrder.getUnitPricePerDay())
            .sum() * durationInDays;
        float servicePrice = (float) reservation.getServiceOrders().stream()
            .mapToDouble(serviceOrder -> serviceOrder.getQuantity() * serviceOrder.getUnitPricePerDay())
            .sum() * durationInDays * reservation.getPeopleNumber();
        float grandTotalPrice = spaceLocationPrice + equipmentPrice + servicePrice;

        reservation.setGrandTotalPrice(grandTotalPrice);

        return reservationRepository.save(reservation);
    }

    private Space trustedSpace(Space space) {
        return spaceRepository.findOne(space.getId());
    }

    /**
     * @param trustedSpace      a Space queried from the database
     * @param equipmentOrderDto an EquipmentOrderDto from the user
     * @return an EquipmentOrder from the trustedSpace and matching the equipmentOrderDto
     */
    private EquipmentOrder trustedEquipmentOrder(Space trustedSpace, EquipmentOrderDto equipmentOrderDto) {
        EquipmentType trustedEquipmentType = trustedSpace
            .getEquipmentTypes()
            .stream()
            .filter(equipmentOrderDto.getEquipmentType()::equals)
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("provided EquipmentOrderDto doesn't match database"));

        return (new EquipmentOrder())
            .equipmentType(trustedEquipmentType)
            .quantity(equipmentOrderDto.getQuantity())
            .unitPricePerDay(trustedEquipmentType.getPrice())
            ;
    }

    private Set<ServiceOrder> trustedServiceOrders(Set<ServiceOrderDto> serviceOrderDtos) {
        Set<ServiceOrder> serviceOrders = new HashSet<>();

        for (ServiceOrderDto serviceOrderDto : serviceOrderDtos) {
            ServiceType serviceType = trustedServiceType(serviceOrderDto.getServiceType());

            ServiceOrder serviceOrder = new ServiceOrder();

            serviceOrder.setServiceType(serviceType);
            serviceOrder.setQuantity(serviceOrderDto.getQuantity());
            serviceOrder.setUnitPricePerDay(serviceType.getPrice());
            serviceOrders.add(serviceOrder);
        }

        return serviceOrders;
    }

    private ServiceType trustedServiceType(ServiceType serviceType) {
        // todo use building repository to find supported EquipmentType, front could be lying
        return serviceTypeRepository.findOne(serviceType.getId());
    }
}
