package com.stackroute.service;


import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> movies=null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie=new Movie();
        movie.setId(1);
        movie.setMovieTitle("Ben10");
        movie.setOverview("Cartoon");
        movie.setLanguage("English");
        movies=new ArrayList<>();
        movies.add(movie);
    }

    @After
    public void tearDown() throws Exception {
        movieRepository.deleteAll();
    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie)any())).thenReturn(movie);
        Movie savedMovie=movieService.saveMovie(movie);
        System.out.println(movieService.getAllMovies());
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);
    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        //        when(movieRepository.save((MovieInfo) any())).thenReturn(null);
        movieService.saveMovie(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        //Assert.assertEquals(user,savedUser);
        movieService.saveMovie(savedMovie);

    }

    @Test
    public void getAllMovies(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> movieList= movieService.getAllMovies();
        Assert.assertEquals(movies,movieList);
    }



    @Test
    public void updateMovieTestSuccess() throws MovieNotFoundException {

        when(movieRepository.existsById(anyInt())).thenReturn(true);
        movieRepository.save(movie);
        System.out.println(movieService.getAllMovies());
        System.out.println(movie.getId());
        movieService.updateMovie(movie);
        //verify here verifies that movieRepository save method is only called once
//        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieNotFoundException.class)
    public void updateMovieTestFailure() throws MovieNotFoundException {
        when(movieRepository.existsById(anyInt())).thenReturn(false);
        Movie savedMovie = movieService.updateMovie(movie);

    }

    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundException {
        when(movieRepository.existsById(anyInt())).thenReturn(true);
        Movie deleteMovie = movieService.deleteMovie((1));
        //verify here verifies that movieRepository save method is only called once
//        verify(movieRepository,times(1)).existsById(1);

    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundException {
        when(movieRepository.existsById(anyInt())).thenReturn(false);
        Movie deleteMovie = movieService.deleteMovie(188);
    }
    @Test
    public void getMovieByNameTestSuccess() throws MovieNotFoundException {
        when(movieRepository.getMovieByName(any())).thenReturn(movie);
        Movie movie1 = movieService.getMovieByName("Ben10");
        Assert.assertEquals(movie1,movie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).getMovieByName("Ben10");
    }
    @Test(expected = MovieNotFoundException.class)
    public void getMovieByNameTestFailure() throws MovieNotFoundException {
//        when(movieRepository.getMovieByName(any())).thenReturn(new Movie());
        Movie movie1 = movieService.getMovieByName("asfdgnh");
    }



}
