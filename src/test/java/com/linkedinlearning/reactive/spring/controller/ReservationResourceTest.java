package com.linkedinlearning.reactive.spring.controller;

import com.linkedinlearning.reactive.spring.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .build();

        reservation = new Reservation(123L,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                150
        );
    }

    @Test
    public void createReservation() {
        webTestClient.post()
                .uri(ReservationResource.ROOM_V_1_RESERVATION)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(Reservation.class);
    }

    @Test
    public void findAll() {
        webTestClient.get()
                .uri(ReservationResource.ROOM_V_1_RESERVATION)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reservation.class);
    }
}