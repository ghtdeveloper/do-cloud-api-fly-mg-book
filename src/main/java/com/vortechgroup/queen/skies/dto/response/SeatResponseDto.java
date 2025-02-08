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
public class SeatResponseDto {
    private Long id;
    private String seatNumber;
    private Boolean available;
}
