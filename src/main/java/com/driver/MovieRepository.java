package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movieDb=new HashMap<>();
    HashMap<String, Director> directorDb=new HashMap<>();
    HashMap<String,String> movieDirectorDb=new HashMap<>();

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

        if(!(movieDb.containsKey(movieName)) || !(directorDb.containsKey(directorName))){
            movieDirectorDb.put(movieName,directorName);
            return "Movie-director pair added successfully";
        }
        if(!movieDirectorDb.containsKey(movieName)){
            movieDirectorDb.put(movieName,directorName);
            return "Movie-director pair added successfully";
        }
        return "Movie-Pair aready exist";
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
        List<String> movieList = new ArrayList<>();
        if(movieDirectorDb.containsValue(directorName)) {

            for (Map.Entry<String, String> entry : movieDirectorDb.entrySet()) {
                if (entry.getValue().equals(directorName)) {
                    String movieName = entry.getKey();
                    movieList.add(movieName);
                }
            }
        }
            return movieList;


    }
    public List<String> findAllMovies(){
        List<String> allMoviesList=new ArrayList<>();

        for(String movieName : movieDb.keySet()){
            allMoviesList.add(movieName);
        }
        return allMoviesList;
    }

    public String deleteDirectorByName(String directorName){

        if(directorDb.containsKey(directorName)) {
            directorDb.remove(directorName);
        }


        for(Map.Entry<String,String> entry : movieDirectorDb.entrySet()){
            if(entry.getValue().equals(directorName)){
                String movieName=entry.getKey();
                if(movieDb.containsKey(movieName)) {
                    movieDb.remove(movieName);
                }
                movieDirectorDb.remove(movieName);
            }
        }

        return "Movies by director "+directorName+" removed successfully";

    }

    public String deleteAllDirectors(){

        for(String directorName:directorDb.keySet()){
            directorDb.remove(directorName);

            for(Map.Entry<String,String> entry : movieDirectorDb.entrySet()){
                if(entry.getValue().equals(directorName)){
                    String movieName=entry.getKey();
                    if(movieDb.containsKey(movieName)) {
                        movieDb.remove(movieName);
                    }
                    movieDirectorDb.remove(movieName);
                }
            }
        }
        return "All directors and their movies have been removed";
    }




}
