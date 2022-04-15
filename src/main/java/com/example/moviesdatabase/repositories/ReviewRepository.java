package com.example.moviesdatabase.repositories;

import com.example.moviesdatabase.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Integer> {

    //List<Review> findByUser_Username(String userName);

}
