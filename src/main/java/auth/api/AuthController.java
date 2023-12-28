package auth.api;

import auth.action.AuthUserAction;
import auth.action.RegisterUserAction;
import auth.api.dto.AuthRequest;
import auth.api.dto.AuthResponse;
import auth.api.dto.RegisterRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthUserAction authUserAction;

    RegisterUserAction registerUserAction;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerUserAction.execute(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authUserAction.execute(request));
    }
}
