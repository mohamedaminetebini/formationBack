package com.example.servertuto.Controllers;

import com.example.servertuto.Dtos.AuthticationResponse;
import com.example.servertuto.Dtos.RegisterRequest;
import com.example.servertuto.Dtos.LoginRequest;
import com.example.servertuto.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthCon {


        private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthticationResponse> register(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
