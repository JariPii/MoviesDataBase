package com.example.moviesdatabase.services;


import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.repositories.ReviewRepository;
import com.example.moviesdatabase.security.PrincipalUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    PrincipalUtil principalUtil;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll(){return reviewRepository.findAll();}

    //public List<Review> findByUser_Username(String userName) {return reviewRepository.findByUser_Username(userName);}

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

    public void deleteById(int id) {
        reviewRepository.deleteById(id);
    }


    public List<Review> findReviewByUsername(String principalName) {
        return reviewRepository.findByUserName(principalName);
    }
}
