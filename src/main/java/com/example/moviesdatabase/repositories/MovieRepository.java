package com.example.moviesdatabase.repositories;

import com.example.moviesdatabase.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Integer> {
}
