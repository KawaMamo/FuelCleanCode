package org.nestech.identityprovider.auth.service;

import lombok.RequiredArgsConstructor;
import org.nestech.identityprovider.auth.requests.AuthenticationRequest;
import org.nestech.identityprovider.auth.requests.RegisterRequest;
import org.nestech.identityprovider.auth.response.AuthenticationResponse;
import org.nestech.identityprovider.config.JWTService;
import org.nestech.identityprovider.user.model.User;
import org.nestech.identityprovider.user.repository.UserRepository;
import org.nestech.identityprovider.user.responses.UserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        final User save = repository.save(user);
        final UserResponse userResponse = new UserResponse(save.getId(), save.getEmail(), save.getRole(), save.isLocked());
        return userResponse;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public String getPublicKey(){
        return jwtService.getPublicKey();
    }

}
