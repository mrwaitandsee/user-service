package builder.userservice.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ActionResponseDto {
    private Boolean success;
    private String message;
}
