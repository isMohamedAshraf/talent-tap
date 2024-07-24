package dev.mohamed.talenttapapi.security.auth;


import dev.mohamed.talenttapapi.security.jwt.JwtService;
import dev.mohamed.talenttapapi.security.role.RoleName;
import dev.mohamed.talenttapapi.security.role.RoleRepository;
import dev.mohamed.talenttapapi.user.Token;
import dev.mohamed.talenttapapi.user.TokenRepository;
import dev.mohamed.talenttapapi.user.User;
import dev.mohamed.talenttapapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;



    public void register(RegistrationRequest request)  {

        var userRoleName = request.isFreelancer() ? RoleName.FREELANCER : RoleName.CLIENT;

        var userRole = roleRepository.findByName(userRoleName)
                .orElseThrow( () -> new IllegalStateException(userRoleName + "Was Not Initialized"));

        var user = User.builder()
                .username(request.username())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole))

                .build();

        userRepository.save(user);
    }



    private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository .save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());

        return new AuthenticationResponse(jwtToken);
    }



}
