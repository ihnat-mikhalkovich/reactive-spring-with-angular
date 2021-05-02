package com.linkedinlearning.reactive.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class HelloController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> hello() {
        return Mono.just("{\"message\" : \"hello\"}");
    }
}
