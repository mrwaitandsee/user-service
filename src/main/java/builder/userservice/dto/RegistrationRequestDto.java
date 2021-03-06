package builder.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class RegistrationRequestDto {
    @NotBlank
    private String uname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
