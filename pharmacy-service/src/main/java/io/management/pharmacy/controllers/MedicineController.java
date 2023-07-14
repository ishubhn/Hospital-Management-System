package io.management.pharmacy.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.management.pharmacy.entities.dto.request.MedicineRequest;
import io.management.pharmacy.entities.dto.response.MedicineResponse;
import io.management.pharmacy.entities.dto.response.MessageResponse;
import io.management.pharmacy.services.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/pharmacy")
public class MedicineController {

    int retry = 1;

    @Autowired
    private MedicineService service;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addNewMedicines(@RequestBody MedicineRequest request) {
        return ResponseEntity.ok(service.addMedicinesInStock(request));
    }

    @GetMapping("/medicines/all")
    @CircuitBreaker(name = "ratingMedicineBreakerAll", fallbackMethod = "ratingMedicineFallbackAll")
    @Retry(name = "ratingMedicineRetryAll")
    @RateLimiter(name = "ratingMedicineLimit")
    public ResponseEntity<List<MedicineResponse>> getAllMedicines() {
        log.info("Calling getAllMedicines");
        log.info("retry attempt: {}", retry);
        retry++;
        return ResponseEntity.ok(service.getAllMedicines());
    }

    public ResponseEntity<List<MedicineResponse>> ratingMedicineFallbackAll(Exception ex) {
        log.error("Fallback is executed for getAllMedicines as the service is down!");
        log.error("Fallback Error Details : {}", ex.getMessage());

        MedicineResponse medicineResponse = MedicineResponse
                .builder()
                .about("Dummy medicine is created because some service is down!")
                .build();

        return new ResponseEntity<>(Collections.singletonList(medicineResponse), HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/medicines/{name}")
    @CircuitBreaker(name = "ratingMedicineBreakerLikeName", fallbackMethod = "ratingMedicineFallbackLikeName")
    @Retry(name = "ratingMedicineRetryLikeName")
    @RateLimiter(name = "ratingMedicineLimit")
    public ResponseEntity<Set<MedicineResponse>> getAllMedicinesLikeName(@PathVariable String name) {
        log.info("Calling getAllMedicinesLikeName for : '{}'", name);
        return ResponseEntity.ok(service.getMedicineLikeName(name));
    }

    public ResponseEntity<Set<MedicineResponse>> ratingMedicineFallbackLikeName(String name, Exception ex) {
        log.error("Fallback is executed for getAllMedicinesLikeName for '{}' as the service is down!", name);
        log.error("Fallback Error Details : {}", ex.getMessage());

        MedicineResponse medicineResponse = MedicineResponse
                .builder()
                .name(name)
                .about("Dummy medicine is created because some service is down!")
                .build();

        return new ResponseEntity<>(Collections.singleton(medicineResponse), HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/medicines/composition/{content}")
    @CircuitBreaker(name = "ratingMedicineBreakerByComposition", fallbackMethod = "ratingMedicineFallbackByComposition")
    @Retry(name = "ratingMedicineRetryByComposition")
    @RateLimiter(name = "ratingMedicineLimit")
    public ResponseEntity<List<MedicineResponse>> getAllMedicinesByComposition(@PathVariable String content) {
        log.info("Calling getAllMedicinesLikeName for : '{}'", content);
        return ResponseEntity.ok(service.getMedicineByComposition(content));
    }

    public ResponseEntity<List<MedicineResponse>> ratingMedicineFallbackByComposition(String content, Exception ex) {
        log.error("Fallback is executed for getAllMedicinesByComposition for {} as the service is down!", content);
        log.error("Fallback Error Details : {}", ex.getMessage());

        MedicineResponse medicineResponse = MedicineResponse
                .builder()
                .about("Dummy medicine is created because some service is down!")
                .build();

        return new ResponseEntity<>(Collections.singletonList(medicineResponse), HttpStatus.BAD_GATEWAY);
    }

    @PutMapping("/medicines/{medicineId}")
    public ResponseEntity<MedicineResponse> deactivateMedicinesInStock(@PathVariable String medicineId) {
        return ResponseEntity.ok(service.deactivateMedicines(medicineId));
    }
}
