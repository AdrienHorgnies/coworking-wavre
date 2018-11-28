package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.api.util.HeaderUtil;
import com.ifosup.coworking.domain.Reservation;
import com.ifosup.coworking.dto.MakeReservationDto;
import com.ifosup.coworking.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    private static final String ENTITY_NAME = "serviceType";
    private final Logger log = LoggerFactory.getLogger(ServiceTypeResource.class);

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * POST /reservations : Create a new reservation
     *
     * @param makeReservationDto the reservation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reservation, or with status 400 (Bad Request) if the reservation has already an ID
     * @throws URISyntaxException if the location URI syntax is incorrect
     */
    @PostMapping("")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody MakeReservationDto makeReservationDto) throws URISyntaxException {
        log.debug("REST request to save Reservation : {}", makeReservationDto);

        Instant now = Instant.now();
        if (makeReservationDto.getStartDate().isBefore(now)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "startBeforeOrder", "A new reservation cannot start before it is made")).body(null);
        }
        if (makeReservationDto.getEndDate().isBefore(makeReservationDto.getStartDate())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "endBeforeStart", "A new reservation cannot end before it starts")).body(null);
        }

        Reservation result = reservationService.save(makeReservationDto);
        return ResponseEntity.created(new URI("/api/reservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
