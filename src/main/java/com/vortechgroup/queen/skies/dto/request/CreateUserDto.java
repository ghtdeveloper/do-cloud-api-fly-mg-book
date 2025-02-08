package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CreateUserDto implements Serializable {

    @NotNull(message = "fullname cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "fullname is invalid")
    private String fullname;

    @NotNull(message = "username cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS, message = "username is invalid")
    private String username;

    @NotNull(message = "roleId cannot be null")
    @PositiveOrZero(message = "roleId must be a non-negative integer")
    private Long roleId;

    @NotNull(message = "activeStatus cannot be null")
    @ActiveStatusValid
    private Character activeStatus;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, max = 16, message = "password must be between 6 and 16 characters")
    private String password;
}
