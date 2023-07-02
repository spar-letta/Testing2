package com.testingjavenock.javenocktesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testingjavenock.javenocktesting.model.Movie;
import com.testingjavenock.javenocktesting.service.ServiceImpl;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MovieControllerTest {

    @MockBean
    private ServiceImpl serviceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNewMovie() throws Exception {
        Movie my_movie = new Movie();
        my_movie.setId(1L);
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

        when(serviceImpl.save(any(Movie.class))).thenReturn(my_movie);

        this.mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(my_movie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(equalTo(my_movie.getName())))
                .andExpect(jsonPath("$.genera").value(equalTo(my_movie.getGenera())));
   }

   @Test
    void shouldFetchAllMovies() throws Exception {
      Movie my_movie = new Movie();
       my_movie.setId(1L);
       my_movie.setName("komando");
       my_movie.setGenera("experts");
       my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

       Movie mymovie = new Movie();
       mymovie.setId(2L);
       mymovie.setName("romio");
       mymovie.setGenera("romance");
       mymovie.setReleaseDate(LocalDate.of(2003, Month.MAY, 22));

       List<Movie> listOfMovies = new ArrayList<>();
       listOfMovies.add(mymovie);
       listOfMovies.add(my_movie);

       when(serviceImpl.findAll()).thenReturn(listOfMovies);

       this.mockMvc.perform(get("/movies"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()").value(equalTo(listOfMovies.size())));
   }

   @Test
    void shouldFindById() throws Exception {
       Movie my_movie = new Movie();
       my_movie.setId(1L);
       my_movie.setName("komando");
       my_movie.setGenera("experts");
       my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

       when(serviceImpl.findMovieById(anyLong())).thenReturn(my_movie);

       this.mockMvc.perform(get("/movies/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(equalTo(my_movie.getName())))
               .andExpect(jsonPath("$.genera").value(equalTo(my_movie.getGenera())));
   }


   @Test
    void deleteMovieTest() throws Exception {
        Movie my_movie = new Movie();
        my_movie.setId(1L);
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

        doNothing().when(serviceImpl).deleteMovie(anyLong());

        this.mockMvc.perform(delete("/movies/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateMovie() throws Exception {
        Movie my_movie = new Movie();
        my_movie.setId(1L);
        my_movie.setName("komando");
        my_movie.setGenera("experts");
        my_movie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 05));

        when(serviceImpl.updateMovie(any(Movie.class), anyLong())).thenReturn(my_movie);

        this.mockMvc.perform(put("/movies/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(my_movie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo(my_movie.getName())))
                .andExpect(jsonPath("$.genera").value(equalTo(my_movie.getGenera())));
    }

}
