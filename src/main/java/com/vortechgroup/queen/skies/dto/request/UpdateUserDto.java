package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UpdateUserDto implements Serializable {

    @PositiveOrZero(message = "id must be a non-negative integer")
    private Long id;

    @PositiveOrZero(message = "roleId must be a non-negative integer")
    private Long roleId;

    @ActiveStatusValid
    private Character activeStatus;

    @Size(min = 6, max = 16,message = "password must be between 6 and 16 characters")
    private String password;
}
