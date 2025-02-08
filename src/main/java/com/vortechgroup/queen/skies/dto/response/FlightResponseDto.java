package com.vortechgroup.queen.skies.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@ToString
public class FlightResponseDto {
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private List<SeatResponseDto> seats;
    private Character activeStatus;
    private LocalDate createdOn;
}
