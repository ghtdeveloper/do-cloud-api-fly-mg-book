package com.vortechgroup.queen.skies.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@ToString
public class UserResponseDto {
    private Long id;
    private String fullname;
    private String username;
    private Long roleId;
    private Character activeStatus;
    private LocalDate createdOn;
}
