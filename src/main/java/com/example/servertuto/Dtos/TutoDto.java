package com.example.servertuto.Dtos;

import lombok.*;

@Builder
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TutoDto {

    private Long id;
    private String title;
    private String description;
    private Boolean publishedStatus;
}
