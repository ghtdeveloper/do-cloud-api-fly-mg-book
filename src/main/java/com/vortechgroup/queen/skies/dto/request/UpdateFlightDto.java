package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
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
public class UpdateFlightDto implements Serializable {

    private Long id;

    @NotNull(message = "departureTime cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "departureTime is invalid")
    private String departureTime;

    @NotNull(message = "arrivalTime cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "arrivalTime is invalid")
    private String arrivalTime;

    @NotNull(message = "activeStatus cannot be null")
    @ActiveStatusValid
    private Character activeStatus;

}
