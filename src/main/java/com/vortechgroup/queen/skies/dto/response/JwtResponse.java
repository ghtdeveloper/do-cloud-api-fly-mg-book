package com.vortechgroup.queen.skies.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class JwtResponse {
    private String token;
}
