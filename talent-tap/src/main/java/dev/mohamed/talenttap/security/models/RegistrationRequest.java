package dev.mohamed.talenttap.security.models;

import dev.mohamed.talenttap.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record RegistrationRequest(
        @NotEmpty(message = "Username is mandatory")
        @NotNull(message = "Username is mandatory")
        String username,
        @NotEmpty(message = "Firstname is mandatory")
        @NotNull(message = "Firstname is mandatory")
        String firstname,
        @NotEmpty(message = "Lastname is mandatory")
        @NotNull(message = "Lastname is mandatory")
        String lastname,
        @Email(message = "Email is not well formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotNull(message = "Email is mandatory")
        String email,
        @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum")
        String password,
        @NotNull(message = "isFreelancer is mandatory")
        boolean isFreelancer

) implements Serializable {
}