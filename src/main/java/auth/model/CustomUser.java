package auth.model;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    UUID id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;

    public static CustomUserDetails getUserDetails(CustomUser user) {
        return CustomUserDetails.builder()
                                .id(user.getId())
                                .role(user.getRole())
                                .username(user.getEmail())
                                .password(user.getPassword())
                                .isAccountNonLocked(true)
                                .isCredentialsNonExpired(true)
                                .isEnabled(true)
                                .isAccountNonExpired(true)
                                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomUser that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
