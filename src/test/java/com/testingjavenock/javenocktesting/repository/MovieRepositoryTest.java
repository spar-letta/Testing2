package com.testingjavenock.javenocktesting.repository;

import com.testingjavenock.javenocktesting.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save(){
        // set the data
        Movie my_movie = new Movie();
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

        Movie newMovie = movieRepository.save(my_movie);

        assertNotNull(newMovie);

        assertThat(newMovie.getId()).isNotEqualTo(null);
    }

    @Test
    void findAllMovies(){
        Movie my_movie = new Movie();
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));
        movieRepository.save(my_movie);

        Movie mymovie = new Movie();
        mymovie.setName("romio");
        mymovie.setGenera("romance");
        mymovie.setReleaseDate(LocalDate.of(2003, Month.MAY, 22));
        movieRepository.save(mymovie);

        List<Movie> savedMovies = movieRepository.findAll();

        assertNotNull(savedMovies);
        assertEquals(2, savedMovies.size());
    }

    @Test
    void findById(){
        Movie my_movie = new Movie();
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));
        movieRepository.save(my_movie);

        Movie fetchedMovie = movieRepository.findById(my_movie.getId()).get();
        assertNotNull(fetchedMovie);
        assertThat(fetchedMovie.getReleaseDate()).isBefore(LocalDate.of(2000, Month.APRIL, 06));
    }

    @Test
    void updateTheMovie(){
        Movie my_movie = new Movie();
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));
        movieRepository.save(my_movie);

        Movie fetchedMovie = movieRepository.findById(my_movie.getId()).get();
        fetchedMovie.setGenera("FANTACY");

        Movie newMovie = movieRepository.save(my_movie);

        assertEquals("FANTACY", newMovie.getGenera() );

    }
}
