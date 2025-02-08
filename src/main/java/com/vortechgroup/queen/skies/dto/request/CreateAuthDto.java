package com.vortechgroup.queen.skies.dto.request;


import com.vortechgroup.queen.skies.utils.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateAuthDto implements Serializable {

    @NotNull(message = "name cannot be null")
    @Pattern(regexp = RegexPattern.REGEX_ONLY_LETTERS,message = "name is invalid")
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, max = 16,message = "password must be between 6 and 16 characters")
    private String password;
}
