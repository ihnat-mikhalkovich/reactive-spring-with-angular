package com.linkedinlearning.reactive.spring.controller;

import com.linkedinlearning.reactive.spring.model.Reservation;
import com.linkedinlearning.reactive.spring.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
@RequiredArgsConstructor
public class ReservationResource {

    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservation(id);
    }

    @PostMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Reservation> updatePrice(@PathVariable String id,
                                         @RequestBody Mono<Reservation> reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Boolean> deleteReservation(@PathVariable String id) {
        return reservationService.deleteReservation(id);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> findAll() {
        return reservationService.findAll();
    }
}
