package builder.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ChangePasswordRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
