package dev.mohamed.talenttap.security.authorizaiton;

import dev.mohamed.talenttap.role.RoleName;
import dev.mohamed.talenttap.role.RoleRepository;
import dev.mohamed.talenttap.security.models.AuthenticationRequest;
import dev.mohamed.talenttap.security.models.AuthenticationResponse;
import dev.mohamed.talenttap.security.models.RegistrationRequest;
import dev.mohamed.talenttap.security.jwt.JwtService;
import dev.mohamed.talenttap.user.User;
import dev.mohamed.talenttap.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
