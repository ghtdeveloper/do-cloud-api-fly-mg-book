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
public class UpdateSeatDto implements Serializable {

    private Long id;

    @NotNull(message = "seatNumber cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "seatNumber is invalid")
    private String seatNumber;

    private Boolean available;
}
