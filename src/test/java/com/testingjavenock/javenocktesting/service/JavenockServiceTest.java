package com.testingjavenock.javenocktesting.service;

import com.testingjavenock.javenocktesting.model.Movie;
import com.testingjavenock.javenocktesting.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavenockServiceTest {

    @InjectMocks
    private ServiceImpl serviceImpl;

    @Mock
    private MovieRepository movieRepository;

    private Movie my_movie;
    private Movie mymovie;

    @BeforeEach
    void init(){

        my_movie = new Movie();
        my_movie.setId(1L);
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

        mymovie = new Movie();
        mymovie.setId(2L);
        mymovie.setName("romio");
        mymovie.setGenera("romance");
        mymovie.setReleaseDate(LocalDate.of(2003, Month.MAY, 22));

    }

    @Test
    @DisplayName("Save Test")
    void saveMovie(){

        when(movieRepository.save(any(Movie.class))).thenReturn(my_movie);

        Movie newMovie = serviceImpl.save(my_movie);

        assertNotNull(newMovie);
        assertThat(newMovie.getName()).isEqualTo("komando");
    }

    @Test
    @DisplayName("Fetch movies")
    void fetchMovies(){

        List<Movie> movieList = new ArrayList<>();
        movieList.add(mymovie);
        movieList.add(my_movie);

        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> returnedList = serviceImpl.findAll();

        assertEquals(2, returnedList.size());
        assertNotNull(returnedList);
    }

    @Test
    @DisplayName("find by id")
    void findMovieById(){

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(my_movie));
        Movie movie = serviceImpl.findMovieById(1L);

        assertNotNull(movie);
        assertThat(movie.getId().equals(1L));
    }

    @Test
    @DisplayName("throw exception")
    void excetionTest(){

        when(movieRepository.findById(1L)).thenReturn(Optional.of(my_movie));
        assertThrows(RuntimeException.class, () -> {
            serviceImpl.findMovieById(2L);
        });
    }

    @Test
    @DisplayName("update movie")
    void updateMovie(){

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(my_movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(my_movie);
        my_movie.setGenera("Ten Commandments");

        Movie updatedMv = serviceImpl.updateMovie(my_movie, 1L);

        assertNotNull(updatedMv);
        assertEquals("Ten Commandments", updatedMv.getGenera());
    }

    @Test
    @DisplayName("delete movie")
    void deleteMovie(){
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(my_movie));
        doNothing().when(movieRepository).delete(any(Movie.class));

        serviceImpl.deleteMovie(1L);

        verify(movieRepository, times(1)).delete(my_movie);
    }
}
