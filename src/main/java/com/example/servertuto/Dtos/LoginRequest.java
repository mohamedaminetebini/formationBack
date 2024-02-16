package com.example.servertuto.Dtos;


import lombok.*;

@Builder
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
