package com.example.moviesdatabase.view;

import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.services.MovieService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.persistence.GeneratedValue;

@Route(value = "movies", layout = MainLayout.class)
public class MoviePage extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class, false);
    MovieService movieService;

    public MoviePage (MovieService movieService) {
        this.movieService = movieService;

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
