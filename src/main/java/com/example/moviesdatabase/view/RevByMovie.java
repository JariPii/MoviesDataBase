package com.example.moviesdatabase.view;


import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.ReviewRepository;
import com.example.moviesdatabase.repositories.AppUserRepository;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("movieRevs")
@AnonymousAllowed
public class RevByMovie extends VerticalLayout {

    Grid<Review> grid = new Grid<>(Review.class, false);
    ReviewRepository reviewRepository;
    AppUserRepository userRepository;
    MovieRepository movieRepository;
    ReviewService reviewService;

    public RevByMovie(ReviewRepository reviewRepository, AppUserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;

    setAlignItems(Alignment.CENTER);
    add(new H1("Reviews so far"));

    //grid.setItems(movieRepository.findBy());

        reviewRepository.findAll().forEach(review -> {

            H2 movieTitle = new H2(review.getMovie().getTitle());
            H3 reviewTitle = new H3(review.getRevTitle());
            Paragraph reviewMessage = new Paragraph(review.getRevMessage());

            Paragraph reviewer = new Paragraph("Reviewed by: ");
            Span author = new Span(review.getUser().getUserName());
            author.getStyle().set("font-weight", "bold");
            reviewer.add(author);

            add(movieTitle, reviewTitle, reviewMessage, reviewer, new Hr());

        });;

    }


}
