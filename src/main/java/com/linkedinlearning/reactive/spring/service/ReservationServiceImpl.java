package com.linkedinlearning.reactive.spring.service;

import com.linkedinlearning.reactive.spring.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReactiveMongoOperations reactiveMongoOperations;

    @Override
    public Mono<Reservation> getReservation(String id) {
        return reactiveMongoOperations.findById(id, Reservation.class);
    }

    @Override
    public Mono<Reservation> createReservation(Mono<Reservation> reservationMono) {
        return reactiveMongoOperations.save(reservationMono);
    }

    @Override
    public Mono<Reservation> updateReservation(String id, Mono<Reservation> reservationMono) {
        // to update the whole reservation we can use
        // reactiveMongoOperations.save(reservationMono)

        // update just price
        return reservationMono.flatMap(reservation ->
                reactiveMongoOperations.findAndModify(
                        Query.query(Criteria.where("id").is(id)),
                        Update.update("price", reservation.getPrice()),
                        Reservation.class
                ).flatMap(result -> {
                    result.setPrice(reservation.getPrice());
                    return Mono.just(result);
                })
        );
    }

    @Override
    public Mono<Boolean> deleteReservation(String id) {
        return reactiveMongoOperations.remove(
                Query.query(Criteria.where("id").is(id)), Reservation.class).
                flatMap(deleteResult -> Mono.just(deleteResult.wasAcknowledged()));
    }

    @Override
    public Flux<Reservation> findAll() {
        return reactiveMongoOperations.findAll(Reservation.class);
    }
}
