package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateFlightDto implements Serializable {

    @NotNull(message = "flightNumber cannot be null")
    private String flightNumber;

    @NotNull(message = "origin cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "origin is invalid")
    private String origin;

    @NotNull(message = "destination cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "destination is invalid")
    private String destination;

    @NotNull(message = "departureTime cannot be null")
    private String departureTime;

    @NotNull(message = "arrivalTime cannot be null")
    private String arrivalTime;

    @NotNull(message = "activeStatus cannot be null")
    @ActiveStatusValid
    private Character activeStatus;

    private LocalDate createdOn;

}
