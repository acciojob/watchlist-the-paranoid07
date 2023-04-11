package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movieDb=new HashMap<>();
    HashMap<String, Director> directorDb=new HashMap<>();
    HashMap<String,List<String>>directorMovieDb =new HashMap<>();

    public String addMovie(Movie movie){
        String key=movie.getName();

        movieDb.put(key,movie);

        return "Movie added successfuly";
    }

    public String addDirector(Director director){
        String key=director.getName();

        directorDb.put(key,director);

        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName){

        if(directorMovieDb.containsKey(directorName)){
            List<String>movies=directorMovieDb.get(directorName);
            movies.add(movieName);
            return "Movie-director pair added successfully";
        }
        List<String>movies=new ArrayList<>();
        movies.add(movieName);
        directorMovieDb.put(directorName,movies);
        return "Movie-director pair added successfully";

        //return "Movie-Pair aready exist";
    }
    public Movie getMovieByName(String movieName){
        if(movieDb.containsKey(movieName)) {
            Movie movie = movieDb.get(movieName);
            return movie;
        }
        return null;
    }

    public Director getDirectorByname(String directorName){

        if(directorDb.containsKey(directorName)){
            Director director=directorDb.get(directorName);
            return director;
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName){
        if(directorMovieDb.containsKey(directorName)) {
            List<String> movieList = directorMovieDb.get(directorName);
            return movieList;
        }

        return new ArrayList<>();

    }
    public List<String> findAllMovies(){
        List<String> allMoviesList=new ArrayList<>();

        for(String movieName : movieDb.keySet()){
            allMoviesList.add(movieName);
        }
        return allMoviesList;
    }

    public String deleteDirectorByName(String directorName){
        if(directorDb.containsKey(directorName)){
            directorDb.remove(directorName);
        }
        if(directorMovieDb.containsKey(directorName)){
            List<String>movieList=directorMovieDb.get(directorName);
            for(String movie:movieList){
                if(movieDb.containsKey(movie)){
                    movieDb.remove(movie);
                }
            }
            directorMovieDb.remove(directorName);
        }



        return "Movies by director "+directorName+" removed successfully";

    }

    public String deleteAllDirectors(){

       for(String directorName:directorDb.keySet()){
           directorDb.remove(directorName);
           if(directorMovieDb.containsKey(directorName)){
               List<String>movieList=directorMovieDb.get(directorName);
               for(String movie:movieList){
                   if(movieDb.containsKey(movie)){
                       movieDb.remove(movie);
                   }
               }
               directorMovieDb.remove(directorName);
           }


       }
        directorMovieDb.clear();
        return "All directors and their movies have been removed";
    }




}
