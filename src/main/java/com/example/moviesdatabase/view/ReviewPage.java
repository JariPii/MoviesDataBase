package com.example.moviesdatabase.view;

import com.example.moviesdatabase.security.PrincipalUtil;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Aj's | Reviews")
@Route(value = "reviews", layout = MainLayout.class)
@AnonymousAllowed
public class ReviewPage extends VerticalLayout {


    public ReviewPage(ReviewService reviewService) {

        add(new H2 ("Current Reviews"));

        setAlignItems(Alignment.CENTER);

        reviewService.findAll().forEach(review -> {

            H2 movieTitle = new H2(review.getMovie().getTitle());
            H3 reviewTitle = new H3(review.getRevTitle());
            Paragraph reviewMessage = new Paragraph(review.getRevMessage());

            Paragraph reviewer = new Paragraph("Reviewed by: ");
            Span author = new Span(review.getUser().getUserName());
            author.getStyle().set("font-weight", "bold");
            reviewer.add(author);

            add(movieTitle, reviewTitle, reviewMessage, reviewer, new Hr());

        });

    }


    /*Grid<Review> grid = new Grid<>(Review.class, false);
    UserService userService;
    ReviewService reviewService;
    MovieService movieService;
    //UserRepository userRepository;
    ReviewForm reviewForm;

    public ReviewPage(ReviewService reviewService*//*, UserRepository userRepository*//*, UserService userService, MovieService movieService) {
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

        HorizontalLayout main = new HorizontalLayout(grid,title*//*,reviewForm*//*);
        main.setSizeFull();

        grid.addColumn(Review::getRevTitle).setHeader("Rev title");
        grid.addColumn(Review::getRevMessage).setHeader("Review");
        grid.addColumn(Review::getUser).setHeader("User");
        //grid.addColumn(Review::getRevPoints).setHeader("Points");
        *//*grid.asSingleSelect().addValueChangeListener(e -> {
            reviewForm.setReview(e.getValue());*//*
        *//*});*//*


        Button button = new Button("New review", e -> {
            Dialog dialog = new Dialog();
            ReviewForm dialogForm = new ReviewForm(reviewService, this);
            dialogForm.setReview(new Review());

            Review review = new Review();
            dialogForm.setReview(review);

            dialog.add(dialogForm);
            dialog.open();

        });*/


        //add(main, button);

    //}

    /*public void updateReview() {
        grid.setItems(reviewService.findAll());
    }*/


}
