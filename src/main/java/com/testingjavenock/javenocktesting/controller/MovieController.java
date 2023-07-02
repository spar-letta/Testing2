package com.testingjavenock.javenocktesting.controller;

import com.testingjavenock.javenocktesting.model.Movie;
import com.testingjavenock.javenocktesting.service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final ServiceImpl service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie){
        return service.save(movie);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> fetchMovies(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findById(@PathVariable Long id){
        return service.findMovieById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable Long id){
        service.deleteMovie(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable Long id){
        return service.updateMovie(movie, id);
    }
}
