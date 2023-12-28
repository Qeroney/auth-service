package auth.service.user;

import auth.model.CustomUser;
import auth.service.user.argument.CreateCustomUserArgument;

import java.util.UUID;

public interface CustomUserService {

    CustomUser create(CreateCustomUserArgument argument);

    CustomUser getExisting(UUID id);
}
