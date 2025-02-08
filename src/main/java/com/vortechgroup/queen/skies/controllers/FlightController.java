package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.request.CreateFlightDto;
import com.vortechgroup.queen.skies.dto.request.UpdateFlightDto;
import com.vortechgroup.queen.skies.dto.response.FlightCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.FlightResponseDto;
import com.vortechgroup.queen.skies.services.FlightService;
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

@Slf4j
@RestController
@RequestMapping(value = "api/v1.0/api/fly/flights", produces = MediaType.APPLICATION_JSON_VALUE)
public record FlightController(FlightService flightService) {
    @PostMapping(value = "/save")
    @Operation(summary = "Method to save a flight", description = "Method to save a flight")
    public ResponseEntity<FlightResponseDto> save(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateFlightDto createFlightDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.flightService.save(createFlightDto));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Method to update a flight", description = "Method to update a flight")
    public ResponseEntity<FlightResponseDto> update(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody UpdateFlightDto updateFlightDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.flightService.update(updateFlightDto));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by flight id", description = "Search by flight id")
    public ResponseEntity<FlightResponseDto> findById(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of flight", description = "Return a list of flight")
    public ResponseEntity<FlightCollectionResponse> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                            @Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.findAll(page, pageSize));
    }

    @GetMapping(value = "/{flightNumber}")
    @Operation(summary = "Search by flight number", description = "Search by flight number")
    public ResponseEntity<FlightResponseDto> findByFlightNumber(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "flightNumber") String flightNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.findFlightByNumber(flightNumber));
    }

}
