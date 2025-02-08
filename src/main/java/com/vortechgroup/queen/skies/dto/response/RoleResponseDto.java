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
public class RoleResponseDto {
    private Long id;
    private String name;
    private Character activeStatus;
}
