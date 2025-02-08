package com.vortechgroup.queen.skies.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@ToString
public class ReservationResponseDto {
    private Long id;
    private FlightResponseDto flightResponseDto;
    private SeatResponseDto seatResponseDto;
    private String passengerName;
    private String reservationCode;
}
