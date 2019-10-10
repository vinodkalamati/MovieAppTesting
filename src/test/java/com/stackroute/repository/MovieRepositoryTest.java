package com.stackroute.repository;


import com.stackroute.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()  {
        movie=new Movie();
        movie.setId(1);
        movie.setMovieTitle("Queen");
        movie.setOverview("bollywood movie");
        movie.setLanguage("Hindi");
    }

    @After
    public void tearDown(){
        movieRepository.deleteAll();
    }


    @Test
    public void testSaveMovie() {
        movieRepository.save(movie);
        Movie fetchMovie=movieRepository.findById(movie.getId()).get();
        Assert.assertEquals(1,fetchMovie.getId());
    }

    @Test
    public void testSaveMovieFailure() {
        Movie testMovie=new Movie(4,"War","Hrithik Roshan and Tiger Shroff","Hindi");
        movieRepository.save(testMovie);
        Assert.assertNotSame(testMovie,movie);
    }

    @Test
    public void testGetAllUser() {
        Movie movie1=new Movie(3,"Syeraa","MegaStar","telugu");
        Movie movie2=new Movie(7,"Bang Bang","Hrithik Roshan","Hindi");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<Movie> movies=movieRepository.findAll();
        Assert.assertEquals("Syeraa",movies.get(0).getMovieTitle());
    }


    @Test
    public void testDeleteMovie(){
        Movie movie1=new Movie(3,"Syeraa","MegaStar","telugu");
        movieRepository.save(movie1);

        movieRepository.delete(movie1);
        Movie fetchMovie=movieRepository.findById(movie1.getId()).orElse(null);
        Assert.assertEquals(null,fetchMovie);
    }

    @Test
    public void testDeleteMovieFailure(){
        Movie movie1 = new Movie(4,"abb","acc","idd");
        movieRepository.save(movie1);
        movieRepository.deleteById(4);
        Boolean aBoolean = movieRepository.findAll().isEmpty();
        Assert.assertNotEquals(false,aBoolean);
    }

    @Test
    public void testUpdateMovie()
    {
        movie.setOverview("kalaaaam");
        movieRepository.save(movie);
        Assert.assertEquals("kalaaaam",movieRepository.findById(1).get().getOverview());
    }

    @Test
    public void testGetByName(){
        Movie movie1 = new Movie(5,"johncena","Based on True Story of John part 1","Hindi");
        movieRepository.save(movie1);
        Movie movie2=movieRepository.getMovieByName("johncena");
        Assert.assertEquals(movie1,movie2);
    }

    @Test
    public void testGetByNameFailure(){
        Movie movie1 = new Movie(5,"johncena","Based on True Story of John part 1","Hindi");
        movieRepository.save(movie1);
        Movie movie2=movieRepository.getMovieByName("johnjohncena");
        Assert.assertNotEquals(movie1,movie2);
    }
}
