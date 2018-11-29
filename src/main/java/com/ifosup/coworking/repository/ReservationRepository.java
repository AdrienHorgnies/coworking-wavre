package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Reservation;
import com.ifosup.coworking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByUser(User user);
}
