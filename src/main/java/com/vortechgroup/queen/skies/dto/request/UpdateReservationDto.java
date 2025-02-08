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
public class UpdateReservationDto implements Serializable {

    private Long id;

    @NotNull(message = "passengerName cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "passengerName is invalid")
    private String passengerName;

    @NotNull(message = "reservationCode cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "reservationCode is invalid")
    private String reservationCode;

}
