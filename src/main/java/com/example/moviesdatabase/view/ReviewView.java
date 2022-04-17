package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.ReviewForm;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.repositories.AppUserRepository;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.ReviewRepository;
import com.example.moviesdatabase.security.PrincipalUtil;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.userdetails.User;

import javax.annotation.security.PermitAll;


@Route(value = "revs", layout = MainLayout.class)
@PermitAll
public class ReviewView extends VerticalLayout {


    Grid<Review> grid = new Grid<>(Review.class, false);
    ReviewService reviewService;
    ReviewForm reviewForm;
    AppUserRepository userRepository;

    public ReviewView(ReviewService reviewService, AppUserRepository userRepository) {
        this.reviewService = reviewService;
        this.userRepository = userRepository;
        this.reviewForm = new ReviewForm(reviewService, this);

        setAlignItems(Alignment.CENTER);
        add(new H1("Edit reviews"));

        grid.setItems(reviewService.findReviewByUsername(userRepository.findAppUserByUserName(PrincipalUtil.getPrincipalName()).orElseThrow()));

        setWidthFull();

        grid.addComponentColumn(review -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE), e -> {
                Notification.show(review.getRevTitle());
                reviewService.deleteById(review.getId());
                updateItems();

            });

            deleteButton.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR
            );

            return deleteButton;

        });


        grid.addColumn(Review::getMovieTitle).setHeader("Movie").setAutoWidth(true);
        grid.addColumn(Review::getRevTitle).setHeader("Review title").setAutoWidth(true);
        grid.addColumn(Review::getRevMessage).setHeader("Review").setAutoWidth(true);
        grid.asSingleSelect().addValueChangeListener(e -> {
            reviewForm.setReview(e.getValue());
        });

        HorizontalLayout main = new HorizontalLayout(grid, reviewForm);
        main.setSizeFull();


        add(main);


    }

    public void updateItems(){grid.setItems(reviewService.findReviewByUsername(userRepository.findAppUserByUserName(PrincipalUtil.getPrincipalName()).orElseThrow()));}

}


