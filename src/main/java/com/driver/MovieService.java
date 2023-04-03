package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
   MovieRepository movieRepository;
    public String addMovie(Movie movie){
        String ans=movieRepository.addMovie(movie);
        return ans;
    }
    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName,String directorName){
       return movieRepository.addMovieDirectorPair(movieName,directorName);
    }
    public Movie getMovieByName(String movieName){
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByname(String directorName){
        return movieRepository.getDirectorByname(directorName);
    }

    public List<Movie> getMoviesByDirectorName(String directorName){
        return movieRepository.getMoviesByDirectorName(directorName);
    }
    public List<Movie> findAllMovies(){
        return findAllMovies();
    }

    public String deleteDirectorByName(String directorName){

        return movieRepository.deleteDirectorByName(directorName);

    }

    public String deleteAllDirectors(){
    return movieRepository.deleteAllDirectors();
    }
}
