package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.request.CreateReservationDto;
import com.vortechgroup.queen.skies.dto.response.ReservationCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.ReservationResponseDto;
import com.vortechgroup.queen.skies.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/v1.0/fly/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public record ReservationController(ReservationService reservationService) {

    @PostMapping(value = "/save")
    @Operation(summary = "Method to save a reservations", description = "Method to save a reservations")
    public ResponseEntity<ReservationResponseDto> save(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateReservationDto createReservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationService.save(createReservationDto));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Method to update a reservations", description = "Method to update a reservations")
    public ResponseEntity<ReservationResponseDto> update(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestParam(name = "reservationCode") String reservationCode) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationService.update(reservationCode));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by reservations id", description = "Search by reservations id")
    public ResponseEntity<ReservationResponseDto> findById(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.reservationService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of reservations", description = "Return a list of reservations")
    public ResponseEntity<ReservationCollectionResponse> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                            @Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(this.reservationService.findAll(page, pageSize));
    }

    @GetMapping(value = "/filter/{flightNumber}")
    @Operation(summary = "Search by flight number", description = "Search by flight number")
    public ResponseEntity<List<ReservationResponseDto>> findByFlightNumber(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "flightNumber") String flightNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(this.reservationService.findReservationByFlightNumber(flightNumber));
    }

}
