package auth.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterRequest {

    String email;

    String password;
}
