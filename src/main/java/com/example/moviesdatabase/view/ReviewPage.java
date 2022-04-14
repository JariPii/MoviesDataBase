package com.example.moviesdatabase.view;

import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "reviews", layout = MainLayout.class)
public class ReviewPage extends VerticalLayout {

    Grid<Review> grid = new Grid<>(Review.class);
    ReviewService reviewService;

    public ReviewPage(ReviewService reviewService) {
        this.reviewService = reviewService;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Coming soon");

        add(title);

    }
}
