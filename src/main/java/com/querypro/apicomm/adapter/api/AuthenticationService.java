package com.querypro.apicomm.adapter.api;

import com.querypro.apicomm.adapter.config.JwtService;
import com.querypro.apicomm.adapter.repository.PromoterRepository;
import com.querypro.apicomm.domain.model.users.Promoter;
import com.querypro.apicomm.domain.model.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PromoterRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var promoter = Promoter.builder()
                .names(request.getNames())
                .lastNames(request.getLastNames())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .tel(request.getTel())
                .bonus(0)
                .role(Role.USER)
                .build();
        repository.save(promoter);
        var jwToken = jwtService.generateToken(promoter);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var promoter = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwToken = jwtService.generateToken(promoter);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .build();
    }
}
