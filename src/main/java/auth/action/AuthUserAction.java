package auth.action;

import auth.api.dto.AuthRequest;
import auth.api.dto.AuthResponse;
import com.example.demo.security.JwtService;
import com.example.demo.user.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthUserAction {

    JwtService jwtService;

    AuthenticationManager authenticationManager;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AuthResponse execute(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                           .token(token)
                           .build();
    }
}
