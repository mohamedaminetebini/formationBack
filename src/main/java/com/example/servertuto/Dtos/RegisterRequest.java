package com.example.servertuto.Dtos;


import lombok.*;

@Builder
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
