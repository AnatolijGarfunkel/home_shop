package project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
