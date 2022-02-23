package builder.userservice.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDto {
    private Boolean success;
    private String message;
}
