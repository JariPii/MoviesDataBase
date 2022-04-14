package com.example.moviesdatabase.view;

import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.services.MovieService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.persistence.GeneratedValue;

@Route(value = "movies", layout = MainLayout.class)
public class MoviePage extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class);
    MovieService movieService;

    public MoviePage (MovieService movieService) {
        this.movieService = movieService;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Movies added soon");

        add(title);

    }

}
