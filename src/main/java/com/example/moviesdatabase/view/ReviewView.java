/*package com.example.moviesdatabase.view;

import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "openRev")
public class ReviewView extends VerticalLayout {

    Grid<Review> grid = new Grid<>(Review.class, false);
    ReviewService reviewService;
    UserRepository userRepository;

    public ReviewView(ReviewService reviewService, UserRepository userRepository) {
        this.reviewService = reviewService;
        this.userRepository = userRepository;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        reviewService.findAll().forEach(review -> {
            H2 reviewTitle = new H2(review.getRevTitle());
            Paragraph movieRev = new Paragraph(review.getRevMessage());

            Paragraph writtenBy = new Paragraph("Written by: ");
            Span author = new Span(review.getUser().getUserName());
            author.getStyle().set("font-weight", "bold");
            writtenBy.add(author);

            add(reviewTitle, movieRev, writtenBy);
        });


    }
}*/
