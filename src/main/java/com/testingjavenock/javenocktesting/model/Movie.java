package com.testingjavenock.javenocktesting.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_movies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genera;

    private LocalDate releaseDate;
}
