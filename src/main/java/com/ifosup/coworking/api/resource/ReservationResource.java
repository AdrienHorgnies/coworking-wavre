package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.api.util.HeaderUtil;
import com.ifosup.coworking.domain.Reservation;
import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.MakeReservationDto;
import com.ifosup.coworking.repository.ReservationRepository;
import com.ifosup.coworking.repository.UserRepository;
import com.ifosup.coworking.service.ReservationService;
import com.ifosup.coworking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.ifosup.coworking.security.AuthoritiesConstants.USER;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    private static final String ENTITY_NAME = "serviceType";
    private final Logger log = LoggerFactory.getLogger(ServiceTypeResource.class);

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public ReservationResource(ReservationService reservationService, ReservationRepository reservationRepository, UserRepository userRepository, UserService userService) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * POST /reservations : Create a new reservation
     *
     * @param makeReservationDto the reservation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reservation, or with status 400 (Bad Request) if the reservation has already an ID
     * @throws URISyntaxException if the location URI syntax is incorrect
     */
    @PostMapping("")
    @Secured(USER)
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody MakeReservationDto makeReservationDto) throws URISyntaxException {
        log.debug("REST request to save Reservation : {}", makeReservationDto);

        Timestamp now = Timestamp.from(Instant.now());
        if (makeReservationDto.getStartDate().before(now)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "startBeforeNow", "A new reservation cannot start before it is made")).build();
        }
        if (makeReservationDto.getEndDate().before(makeReservationDto.getStartDate())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "endBeforeStart", "A new reservation cannot end before it starts")).build();
        }

        Reservation result = reservationService.save(makeReservationDto);
        return ResponseEntity.created(new URI("/api/reservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET /reservations/own
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the authenticated user's reservations
     */
    @GetMapping("own")
    @Secured(USER)
    public ResponseEntity<List<Reservation>> getOwnReservations() {
        log.debug("REST request to get authenticated user reservations");

        return ResponseEntity.ok(reservationRepository.getAllByUser(userService.getCurrentUser()));
    }

    /**
     * GET /reservations/{id}
     *
     * @param id the id of the reservation we are looking for
     * @return the ResponseEntity with status 200 (Ok) and with body the reservation if the authenticated user owns it
     */
    @GetMapping("{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        log.debug("REST request to get reservation {}", id);

        Reservation reservation = reservationRepository.findOne(id);
        User currentUser = userService.getCurrentUser();

        if (!currentUser.id.equals(reservation.getUser().id)) {
            return ResponseEntity.notFound().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "notFoundForCurrentUser", "No reservation with such id has been found for current user")).build();
        }

        return ResponseEntity.ok(reservation);
    }
}
