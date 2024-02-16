package com.example.servertuto.Dtos;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthticationResponse {

    private String token;
}
