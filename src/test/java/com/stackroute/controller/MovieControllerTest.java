package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.service.MovieService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list=null;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie();
        movie.setId(1);
        movie.setMovieTitle("King");
        movie.setOverview("qwerty qwerty qwerty");
        movie.setLanguage("English");
        list = new ArrayList();
        list.add(movie);
    }

    @Test
    public void saveMovie() throws Exception{
        when(movieService.saveMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void saveUserFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUser() throws Exception {
        when(movieService.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateMovie() throws Exception{
        when(movieService.updateMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMovieFailure() throws Exception{
        when(movieService.updateMovie(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovie() throws Exception{
        when(movieService.deleteMovie(anyInt())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovieFailure() throws Exception{
        when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieByName() throws Exception{
        when(movieService.getMovieByName((anyString()))).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getMovieByNameFailure() throws Exception{
        when(movieService.getMovieByName((anyString()))).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
















    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
