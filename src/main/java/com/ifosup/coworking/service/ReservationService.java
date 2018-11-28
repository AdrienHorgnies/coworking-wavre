package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Reservation;
import com.ifosup.coworking.dto.MakeReservationDto;
import com.ifosup.coworking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation save(MakeReservationDto makeReservationDto) {
        Instant now = Instant.now();

        Reservation reservation = new Reservation();
        reservation.setTitle(makeReservationDto.getTitle());
        reservation.setOrderDate(now);
        reservation.setStartDate(makeReservationDto.getStartDate());
        reservation.setEndDate(makeReservationDto.getEndDate());
        reservation.setSpace(makeReservationDto.getSpace());
        reservation.setEquipmentPacks(makeReservationDto.getEquipmentPacks());
        reservation.setServiceTypes(makeReservationDto.getServiceTypes());

        return reservationRepository.save(reservation);
    }
}
