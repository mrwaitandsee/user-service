package builder.userservice.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RegistrationResponseDto {
    private UUID id;
    private String uname;
    private String email;
}
