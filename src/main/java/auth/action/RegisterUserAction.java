package auth.action;

import auth.api.dto.AuthRequest;
import auth.api.dto.AuthResponse;
import auth.api.dto.RegisterRequest;
import auth.service.user.CustomUserService;
import auth.service.user.argument.CreateCustomUserArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegisterUserAction {

    AuthUserAction authUserAction;

    CustomUserService customUserService;

    PasswordEncoder encoder;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AuthResponse execute(RegisterRequest request) {
        customUserService.create(CreateCustomUserArgument.builder()
                                                         .email(request.getEmail())
                                                         .password(encoder.encode(request.getPassword()))
                                                         .build());
        return authUserAction.execute(AuthRequest.builder()
                                                 .email(request.getEmail())
                                                 .password(request.getPassword())
                                                 .build());

    }
}
