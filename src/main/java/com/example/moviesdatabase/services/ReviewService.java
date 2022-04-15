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

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateById(int id, Review changedReview) {

        Review existingReview = reviewRepository.findById(id).orElseThrow();

        if(changedReview.getRevTitle() != null)
            existingReview.setRevTitle(changedReview.getRevTitle());
        if(changedReview.getRevMessage() != null)
            existingReview.setRevMessage(changedReview.getRevMessage());

        reviewRepository.save(existingReview);

        return existingReview;

    }





    /*public List<Review> findReviewByUsername(String userName) {
        return reviewRepository.findByUser_Username(userName);
    }*/
}
