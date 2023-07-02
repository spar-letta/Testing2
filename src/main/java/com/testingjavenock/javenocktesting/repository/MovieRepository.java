package com.testingjavenock.javenocktesting.repository;

import com.testingjavenock.javenocktesting.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
