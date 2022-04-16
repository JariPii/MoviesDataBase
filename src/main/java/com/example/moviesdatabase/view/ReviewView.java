package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.ReviewForm;
import com.example.moviesdatabase.components.ReviewForm;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.security.PrincipalUtil;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;

import javax.annotation.security.PermitAll;

//import javax.annotation.security.PermitAll;

//@PermitAll

@Route(value = "revs", layout = MainLayout.class)
@AnonymousAllowed
public class ReviewView extends VerticalLayout {

    ReviewService reviewService;
    Grid<Review> grid = new Grid<>(Review.class, false);
    ReviewForm reviewForm;
    UserService userService;
    UserRepository userRepository;
    MovieRepository movieRepository;
    PrincipalUtil principalUtil;

    public ReviewView(ReviewService reviewService, MovieRepository movieRepository, UserRepository userRepository/*, PrincipalUtil principalUtil*/) {
        this.reviewService = reviewService;
        this.movieRepository = movieRepository;
        this.principalUtil = principalUtil;
        this.userRepository = userRepository;
        this.reviewForm = new ReviewForm(reviewService, this);
        setAlignItems(Alignment.CENTER);
        add(new H1("Edit reviews"));


        grid.setItems(reviewService.findAll());

        grid.addComponentColumn(review -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE), e -> {
                reviewService.deleteById(review.getId());
               // updateItems();

            });

            deleteButton.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR
            );

            return deleteButton;

        });

        grid.addColumn(Review::getRevTitle).setHeader("Title");
        grid.addColumn(Review::getRevMessage).setHeader("Review");
        grid.addColumn(Review::getUser).setHeader("User");
        //grid.addColumn(Review::getMovie).setHeader("Movie");
        grid.asSingleSelect().addValueChangeListener(e -> {
            reviewForm.setReview(e.getValue());
        });

        HorizontalLayout main = new HorizontalLayout(grid, reviewForm);
        main.setSizeFull();

        Button button = new Button("Add new review", e -> {
            Dialog dialog = new Dialog();
            //ReviewForm reviewForm = new ReviewForm(reviewService);
            Review review = new Review();

            //User currentUser = userRepository.findByUserName(PrincipalUtil.getPrincipalName()).orElseThrow();
            //review.setUser(currentUser);
            reviewForm.setReview(review);
            dialog.add(reviewForm);
            dialog.open();
        });

        add(button);

        add(new H2 ("Current Reviews"));

        setAlignItems(Alignment.CENTER);

        reviewService.findAll().forEach(review -> {

            //H2 movieTitle = new H2(String.join(review.getMovie().getTitle()));
            H3 reviewTitle = new H3(review.getRevTitle());
            Paragraph reviewMessage = new Paragraph(review.getRevMessage());

            Paragraph reviewer = new Paragraph("Reviewed by: ");
            Span author = new Span(PrincipalUtil.getPrincipalName());
            author.getStyle().set("font-weight", "bold");
            reviewer.add(author);

            add( reviewTitle, reviewMessage, reviewer, new Hr());

        });;


    }

/*    public void updateItems(){grid.setItems(reviewService.findReviewByUsername(PrincipalUtil.getPrincipalName()));}*/


}


