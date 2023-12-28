package auth.service.details;

import auth.exception.UserNotFoundException;
import auth.model.CustomUser;
import auth.repository.CustomUserRepository;
import com.example.demo.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository repository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return CustomUser.getUserDetails(repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found")));
    }
}
