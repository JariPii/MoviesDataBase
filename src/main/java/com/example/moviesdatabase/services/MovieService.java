package com.example.moviesdatabase.services;

import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {return movieRepository.findAll();}

}
