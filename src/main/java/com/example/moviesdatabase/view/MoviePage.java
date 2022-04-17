package com.example.moviesdatabase.view;


import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.AppUserRepository;
import com.example.moviesdatabase.security.PrincipalUtil;
import com.example.moviesdatabase.services.MovieService;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Aj's | Movies")
@Route(value = "movies", layout = MainLayout.class)
@AnonymousAllowed
public class MoviePage extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class, false);
    MovieService movieService;
    ReviewService reviewService;
    AppUserRepository userRepository;
    MovieRepository movieRepository;

    public MoviePage (MovieService movieService, ReviewService reviewService, AppUserRepository userRepository, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewService = reviewService;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Movies added soon");

        grid.setItems(movieService.findAll());
        grid.setWidthFull();

        HorizontalLayout main = new HorizontalLayout(grid, title);
        main.setSizeFull();

        grid.addColumn(Movie::getTitle).setHeader("Movie title");
        grid.addColumn(Movie::getDirector).setHeader("Movie director");
        grid.addColumn(Movie::getReleaseYear).setHeader("Movie released");
        grid.addColumn(Movie::getGenre).setHeader("Movie genre");

        AddReviewForm addReviewForm = new AddReviewForm(reviewService, this);
        grid.asSingleSelect().addValueChangeListener(e -> {

            if(PrincipalUtil.isLoggedIn()) {

                Dialog dialog = new Dialog();
                Review review = new Review();

                AppUser currentUser = userRepository.findAppUserByUserName(PrincipalUtil.getPrincipalName()).orElseThrow();

                review.setMovie(e.getValue());
                review.setUser(currentUser);


                addReviewForm.setReview(review);
                dialog.add(addReviewForm);
                dialog.open();
            } else {
                UI.getCurrent().navigate(RevByMovie.class);

            }

        });

        add(main);

    }


    /*public void updateList() {
        grid.setItems(reviewService.findReviewByUsername(PrincipalUtil.getPrincipalName()));
    }*/
}


