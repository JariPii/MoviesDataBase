package com.example.moviesdatabase.repositories;

import com.example.moviesdatabase.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Integer> {

    //List<Movie> findByMovieTitle(String title);

}
