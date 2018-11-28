package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
