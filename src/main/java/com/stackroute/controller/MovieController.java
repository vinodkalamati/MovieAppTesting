package com.stackroute.controller;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MovieController {

//    @Qualifier("movieService") //if we use primary annotation no need of Qualifier
    MovieService movieService;
    private ResponseEntity responseEntity;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //create Movie
    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) throws MovieAlreadyExistsException {
//        ResponseEntity responseEntity;
//        try {
//            movieService.saveMovie(movie);
//            responseEntity=new ResponseEntity("Successfully Created", HttpStatus.CREATED);
//        }
//        catch (MovieAlreadyExistsException ex){
//            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;
        movieService.saveMovie(movie);
        responseEntity= new ResponseEntity<String>("Successfully created!", HttpStatus.CREATED);
        return responseEntity;
    }


    //Retrieve Movie

    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies(){
//        List<Movie> retrievedMovie=movieService.getAllMovies();
//        ResponseEntity responseEntity;
//        try{
//            movieService.getAllMovies();
//            responseEntity=new ResponseEntity<List<Movie>>(retrievedMovie,HttpStatus.OK);
//        }
//        catch (Exception ex){
//            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);

    }

    //Update Movie
    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie) throws MovieNotFoundException {
//        ResponseEntity responseEntity;
//        try {
//            movieService.updateMovie(movie);
//            responseEntity=new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
//        }
//        catch (MovieNotFoundException ex){
//            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;

        movieService.updateMovie(movie);
        responseEntity= new ResponseEntity<String>("Updated Successfully",HttpStatus.ACCEPTED);
        return responseEntity;
    }

    //DeleteMovie
    @DeleteMapping("movie")
    public ResponseEntity<?> getDeleteById(@RequestBody int id) throws MovieNotFoundException {
//        ResponseEntity responseEntity;
//
//        try{
//            movieService.deleteMovie(id);
//            responseEntity=new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
//        }
//        catch (MovieNotFoundException ex){
//            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;
        movieService.deleteMovie(id);
        responseEntity= new ResponseEntity<String>("Deleted Successfully",HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping("movie/{movieTitle}")
    public ResponseEntity<?> getMovieByName(@PathVariable("movieTitle") String movieTitle) throws MovieNotFoundException {
//        ResponseEntity responseEntity;
//        try{
//            Movie movie=movieService.getMovieByName(movieTitle);
//            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.FOUND);
//        }
//        catch (MovieNotFoundException ex){
//            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;

        Movie movie1= movieService.getMovieByName(movieTitle);
        responseEntity= new ResponseEntity<Movie>(movie1,HttpStatus.ACCEPTED);
        return responseEntity;


    }



}
