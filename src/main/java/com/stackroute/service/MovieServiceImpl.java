package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
@Profile("temp")
@Primary //we can use primary if movieService has more than one implement
public class MovieServiceImpl implements MovieService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {


    @Value("${movie.1.movieTitle:default}")
    String title1;
    @Value("${movie.1.overview:default}")
    String overview1;
    @Value("${movie.1.language:default}")
    String language1;

    @Value("${movie.2.movieTitle:default}")
    String title2;
    @Value("${movie.2.overview:default}")
    String overview2;
    @Value("${movie.2.language:default}")
    String language2;



    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //create Movie
    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieAlreadyExistsException("Movie already exits unable to save");
        }
            Movie movie1=movieRepository.save(movie);
        if (movie1==null){
            throw  new MovieAlreadyExistsException("Movie already exits unable to save");
        }
            return movie1;

    }

    //Retrieve AllMovies
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }


    //Delete Movie
    @Override
    public Movie deleteMovie(int id) throws MovieNotFoundException {
        Movie movie;
        System.out.println(movieRepository.existsById(id));
        if (movieRepository.existsById(id)){
            movie=movieRepository.findById(id).orElse(new Movie());
            movieRepository.deleteById(id);
        }
        else {
            throw new MovieNotFoundException("No movie found with Id "+id);
        }
        return movie;
    }

    //Update Movie
    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
//        Movie movie1=movieRepository.findById(movie.getId()).orElseThrow(()->new MovieNotFoundException("No movie found with Id "+movie.getId()));
//        movieRepository.save(movie1);
//        return movie1;
        if(movieRepository.existsById(movie.getId()))
        {
            return movieRepository.save(movie);
        }
        else
        {
            throw  new MovieNotFoundException("Movie Does Not Exist!");
        }

    }

    //movie by name
    @Override
    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException {
        Movie movie=movieRepository.getMovieByName(movieTitle);
        if (movie==null)
        {
            throw new MovieNotFoundException("No movie found with MovieTitle "+movieTitle);
        }
        return movie;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        movieRepository.save(new Movie(1,title2,overview2,language2));
    }

    @Override
    public void run(String... args) throws Exception {

        movieRepository.save(new Movie(2,title1,overview1,language1));
    }
}
