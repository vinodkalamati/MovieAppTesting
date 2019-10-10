package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies();
    public Movie deleteMovie(int id) throws MovieNotFoundException;
    public Movie updateMovie(Movie movie) throws MovieNotFoundException;
    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException;

}
