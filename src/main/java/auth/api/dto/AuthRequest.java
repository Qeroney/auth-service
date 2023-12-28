package auth.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthRequest {

    String email;

    String password;
}
