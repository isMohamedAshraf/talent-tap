package dev.mohamed.talenttap.security.models;

import dev.mohamed.talenttap.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class AuthenticationRequest implements Serializable {

    @NotEmpty(message = "Username is mandatory")
    @NotNull(message = "Username is mandatory")
    String username;

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    String password;
}