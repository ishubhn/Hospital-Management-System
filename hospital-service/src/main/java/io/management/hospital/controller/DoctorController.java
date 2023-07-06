package io.management.hospital.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.management.hospital.entities.dto.request.DoctorRequest;
import io.management.hospital.entities.dto.response.DoctorResponse;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hospital/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/search/all")
    @CircuitBreaker(name = "ratingDoctorBreakerAll", fallbackMethod = "ratingDoctorFallbackAll")
    @Retry(name = "ratingDoctorRetryAll")
    @RateLimiter(name = "ratingDoctorLimit")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        log.info("Calling all users");
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // Fallback Method
    // Fallback Method must have same return type and params
    public ResponseEntity<List<DoctorResponse>> ratingDoctorFallbackAll(Exception ex) {
        log.warn("Retry attempt exhausted");
        log.error("Fallback is executed for getAllDoctors as the service is down!");
        log.error("Fallback Error Details : {}", ex.getMessage());

        DoctorResponse response = DoctorResponse
                .builder()
                .about("Dummy user is created because some service is down!")
                .build();

        // Collections.singletonList(T t) -> returns immutable list with single element
        return new ResponseEntity(Collections.singletonList(response), HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/search/id/{id}")
    @CircuitBreaker(name = "ratingDoctorBreakerById", fallbackMethod = "ratingDoctorFallbackId")
    @Retry(name = "ratingDoctorRetryById")
    @RateLimiter(name = "ratingDoctorLimit")
    public ResponseEntity<DoctorResponse> getDoctorDetailsById(@PathVariable String id) {
        log.info("Calling getDoctorDetailsById for user: {}", id);
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    // Fallback Method
    public ResponseEntity<DoctorResponse> ratingDoctorFallbackId(String id, Exception ex) {
        log.warn("Retry attempt exhausted");
        log.error("Fallback is executed for getDoctorDetailsById as the service is down!");
        log.error("Fallback Error Details : {}", ex.getMessage());

        DoctorResponse user = new DoctorResponse(id, "Dummy user is created because some service is down!");

        return new ResponseEntity<>(user, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/search/email/{emailId}")
    @CircuitBreaker(name = "ratingDoctorBreakerByEmail", fallbackMethod = "ratingDoctorFallbackEmail")
    @Retry(name = "ratingDoctorRetryByEmail")
    @RateLimiter(name = "ratingDoctorLimit")
    public ResponseEntity<DoctorResponse> getDoctorDetailsByEmail(@PathVariable String emailId) {
        log.info("Calling getDoctorDetailsByEmail for user: {}", emailId);
        return ResponseEntity.ok(doctorService.getDoctorByEmailId(emailId));
    }

    public ResponseEntity<DoctorResponse> ratingDoctorFallbackEmail(String emailId, Exception ex) {
        log.warn("Retry attempt exhausted");
        log.error("Fallback is executed for getDoctorDetailsByEmail as the service is down!");
        log.error("Fallback Error Details : {}", ex.getMessage());

        DoctorResponse user = new DoctorResponse(emailId, "Dummy user is created because some service is down!");

        return new ResponseEntity<>(user, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/search/name")
    @CircuitBreaker(name = "ratingDoctorBreakerByEitherName", fallbackMethod = "ratingDoctorFallbackEitherName")
    @Retry(name = "ratingDoctorRetryByEitherName")
    @RateLimiter(name = "ratingDoctorLimit")
    public ResponseEntity<List<DoctorResponse>> getDoctorDetailsByEitherName
            (@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Calling getDoctorDetailsByEitherName for user: firstname - {} or lastname - {}", firstName, lastName);
        return ResponseEntity.ok(doctorService.getDoctorsLikeName(firstName, lastName));
    }

    public ResponseEntity<List<DoctorResponse>> ratingDoctorFallbackEitherName(String firstName, String lastName,
                                                                               Exception ex) {
        log.warn("Retry attempt exhausted");
        log.error("Fallback is executed for getDoctorDetailsByEitherName as the service is down!");
        log.error("Fallback Error Details : {}", ex.getMessage());

        DoctorResponse user = DoctorResponse
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .about("Dummy user is created because some service is down!").build();

        return new ResponseEntity<>(Collections.singletonList(user), HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addDoctor(@RequestBody DoctorRequest doctorRequest) {
        return new ResponseEntity<>(doctorService.createDoctor(doctorRequest), HttpStatus.CREATED);
    }

    @PostMapping("/add/{doctorId}/{hospitalId}")
    public ResponseEntity<MessageResponse> enrollDoctorToHospital(@PathVariable String doctorId,
                                                                  @PathVariable String addressId) {
        log.info("Calling enrollDoctorToHospital for user: doctorId - {} or addressId - {}", doctorId, addressId);
        return new ResponseEntity<>(doctorService.enrollDoctorToHospital(doctorId, addressId),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/doctor/qual")
    public ResponseEntity<MessageResponse> addDoctorQualification(@RequestParam String doctorId,
                                                                  @RequestParam String degree,
                                                                  @RequestParam String specializationField) {
        return new ResponseEntity<>(doctorService.addDoctorQualification(doctorId, degree, specializationField),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteDoctorById(@PathVariable String id) {
        return new ResponseEntity<>(doctorService.deleteDoctor(id), HttpStatus.OK);
    }

}
