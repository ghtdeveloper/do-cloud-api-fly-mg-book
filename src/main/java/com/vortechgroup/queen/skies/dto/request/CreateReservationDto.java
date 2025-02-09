package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateReservationDto implements Serializable {

    @NotNull(message = "flightNumber cannot be null")
    private String flightNumber;

    @NotNull(message = "seatNumber cannot be null")
    private String seatNumber;

    @NotNull(message = "passengerName cannot be null")
    private String passengerName;

    @NotNull(message = "reservationCode cannot be null")
    private String reservationCode;

}
