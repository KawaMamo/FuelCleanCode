package org.nestech.identityprovider.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nestech.identityprovider.auth.service.AuthenticationService;
import org.nestech.identityprovider.auth.requests.AuthenticationRequest;
import org.nestech.identityprovider.auth.requests.RegisterRequest;
import org.nestech.identityprovider.auth.response.AuthenticationResponse;
import org.nestech.identityprovider.user.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping(value = "/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


    @GetMapping("/auth/publicKey")
    public ResponseEntity<String> publicKey(){
        return ResponseEntity.ok(service.getPublicKey());
    }

    @ExceptionHandler
    public ErrorResponse handleInvalidTopTalentDataException(MethodArgumentNotValidException validException) {
        return ErrorResponse
                .create(new Exception(),
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(Objects.requireNonNull(validException.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map((s)-> s.getField()+": "+s.getDefaultMessage())
                                .reduce((s1, s2)-> s1+" , "+s2))));
    }

}
