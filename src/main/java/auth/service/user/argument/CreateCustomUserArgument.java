package auth.service.user.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomUserArgument {

    String email;

    String password;
}
