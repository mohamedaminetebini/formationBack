package com.example.servertuto.models;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "tuto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TutoModel {


    @Id
    @SequenceGenerator(name = "tuto_id_seq", sequenceName = "tuto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tuto_id_seq")
    private Long id;

    @Column(name = "title",nullable = false,unique = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "published_status",nullable = false)
    private Boolean publishedStatus;
}
