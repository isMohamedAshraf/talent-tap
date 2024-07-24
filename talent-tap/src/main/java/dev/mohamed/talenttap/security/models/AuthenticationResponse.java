package dev.mohamed.talenttap.security.authorizaiton.models;

import java.io.Serializable;

public record AuthenticationResponse(
        String token
) implements Serializable {
}
