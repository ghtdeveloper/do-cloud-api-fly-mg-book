package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.ActiveStatusValid;
import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateRoleDto implements Serializable {

    private Long id;

    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS,message = "name is invalid")
    private String name;

    @ActiveStatusValid
    private Character activeStatus;
}
