package com.example.servertuto.Services;

import com.example.servertuto.Dtos.AuthticationResponse;
import com.example.servertuto.Dtos.LoginRequest;
import com.example.servertuto.Dtos.RegisterRequest;
import com.example.servertuto.Repositories.UserRepo;
import com.example.servertuto.models.Role;
import com.example.servertuto.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthticationResponse register(RegisterRequest request) {
        System.out.println(request.getPassword());
        UserModel user = UserModel.builder().email(request.getEmail()).username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(Role.ROLE_USER).build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthticationResponse.builder().token(jwtToken).build();
    }

    public AuthticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthticationResponse.builder().token(jwtToken).build();
    }
}
