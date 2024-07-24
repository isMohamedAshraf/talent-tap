package dev.mohamed.talenttap.security.models;

import java.io.Serializable;

public record AuthenticationResponse(
        String token
) implements Serializable {
}
