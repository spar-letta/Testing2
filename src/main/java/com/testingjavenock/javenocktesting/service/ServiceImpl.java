package com.testingjavenock.javenocktesting.service;

import com.testingjavenock.javenocktesting.model.Movie;
import com.testingjavenock.javenocktesting.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImpl {

    private final MovieRepository movieRepository;

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("no movie found with such id"));
    }

    public Movie updateMovie(Movie movie, Long id){
        Movie existingMovie = movieRepository.findById(id).get();
        existingMovie.setGenera(movie.getGenera());
        existingMovie.setName(movie.getName());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id){
        Movie existingMovie = movieRepository.findById(id).get();
        movieRepository.delete(existingMovie);
    }
}
