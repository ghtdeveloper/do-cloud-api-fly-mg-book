package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateRoleDto implements Serializable {

    @NotNull(message = "name cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS_AND_SPACE, message = "name is invalid")
    private String name;

    @NotNull(message = "activeStatus cannot be null")
    @ActiveStatusValid
    private Character activeStatus;
}
