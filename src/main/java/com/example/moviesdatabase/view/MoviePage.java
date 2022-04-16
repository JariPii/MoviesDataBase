package com.example.moviesdatabase.view;


import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.services.MovieService;
import com.example.moviesdatabase.services.ReviewService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;
import javax.persistence.GeneratedValue;
import java.awt.*;
import java.lang.management.BufferPoolMXBean;

@PageTitle("Aj's | Movies")
@Route(value = "movies", layout = MainLayout.class)
@PermitAll
public class MoviePage extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class, false);
    MovieService movieService;
    ReviewService reviewService;

    public MoviePage (MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
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


        add(main);

    }

}
