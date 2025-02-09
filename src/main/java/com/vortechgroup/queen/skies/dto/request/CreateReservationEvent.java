package com.vortechgroup.queen.skies.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReservationEvent {
    private String reservationCode;
    private String passengerName;
    private String flightNumber;
    private String seatNumber;
}
