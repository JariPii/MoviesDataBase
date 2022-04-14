package com.example.moviesdatabase.services;


import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll(){return reviewRepository.findAll();}
}
