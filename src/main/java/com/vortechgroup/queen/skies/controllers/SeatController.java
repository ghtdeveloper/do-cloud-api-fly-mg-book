package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.response.SeatCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.SeatResponseDto;
import com.vortechgroup.queen.skies.services.SeatService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = "api/v1.0/api/fly/seats", produces = MediaType.APPLICATION_JSON_VALUE)
public record SeatController(SeatService seatService) {

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by seat id", description = "Search by seat id")
    public ResponseEntity<SeatResponseDto> findById(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.seatService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of seats", description = "Return a list of users")
    public ResponseEntity<SeatCollectionResponse> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                          @Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(this.seatService.findAll(page, pageSize));
    }

    @GetMapping(value = "/{flightNumber}")
    @Operation(summary = "Search seats by flightNumber", description = "Search seats by flightNumber")
    public ResponseEntity<SeatResponseDto> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "flightNumber") String flightNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(this.seatService.findByFlightNumber(flightNumber));
    }

}
