package auth.service.user;

import auth.exception.UserNotFoundException;
import auth.model.CustomUser;
import auth.repository.CustomUserRepository;
import auth.service.user.argument.CreateCustomUserArgument;
import com.example.demo.user.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserRepository repository;

    @Override
    @Transactional
    public CustomUser create(@NonNull CreateCustomUserArgument argument) {
        return repository.save(CustomUser.builder()
                                         .email(argument.getEmail())
                                         .password(argument.getPassword())
                                         .role(Role.USER)
                                         .build());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomUser getExisting(@NonNull UUID id) {
        return repository.findById(id)
                         .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
