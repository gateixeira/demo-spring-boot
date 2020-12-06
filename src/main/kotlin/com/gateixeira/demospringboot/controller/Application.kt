package com.gateixeira.demospringboot.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

@RestController
class Application {
    object Counter {
        public var value = 0
        fun count(): Int = value++
    }

    @GetMapping("/")
    fun home(): String {
        return "Hello World";
    }

    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        Counter.count();

        if (Counter.value > 15) {
            println("Service not healthy. Count: " + Counter.value);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Not healthy");
        } else {
            println("Service is healthy. Count: " + Counter.value);
            return ResponseEntity.status(HttpStatus.OK).body("Healthy");
        }
    }

    @GetMapping("/ready")
    fun ready(): ResponseEntity<String> {

        if (Counter.value > 10) {
            println("Service not ready. Count: " + Counter.value);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Not ready");
        } else {
            println("Service is ready. Count: " + Counter.value);
            return ResponseEntity.status(HttpStatus.OK).body("Ready");
        }
    }
}