package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.ReviewForm;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.services.MovieService;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "reviews", layout = MainLayout.class)
public class ReviewPage extends VerticalLayout {

    Grid<Review> grid = new Grid<>(Review.class, false);
    UserService userService;
    ReviewService reviewService;
    MovieService movieService;
    //UserRepository userRepository;
    ReviewForm reviewForm;

    public ReviewPage(ReviewService reviewService/*, UserRepository userRepository*/, UserService userService, MovieService movieService) {
        //this.userRepository = userRepository;
        this.movieService = movieService;
        this.userService = userService;
        this.reviewService = reviewService;
        this.reviewForm = new ReviewForm(reviewService, this);

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Coming soon");


        grid.setItems(reviewService.findAll());
        grid.setWidthFull();

        HorizontalLayout main = new HorizontalLayout(grid,title/*,reviewForm*/);
        main.setSizeFull();

        grid.addColumn(Review::getRevTitle).setHeader("Rev title");
        grid.addColumn(Review::getRevMessage).setHeader("Review");
        grid.addColumn(Review::getUser).setHeader("User");
        //grid.addColumn(Review::getRevPoints).setHeader("Points");
        /*grid.asSingleSelect().addValueChangeListener(e -> {
            reviewForm.setReview(e.getValue());*/
        /*});*/


        Button button = new Button("New review", e -> {
            Dialog dialog = new Dialog();
            ReviewForm dialogForm = new ReviewForm(reviewService, this);
            dialogForm.setReview(new Review());

            Review review = new Review();
            dialogForm.setReview(review);

            dialog.add(dialogForm);
            dialog.open();

        });


        add(main, button);

    }

    public void updateReview() {
        grid.setItems(reviewService.findAll());
    }
}
