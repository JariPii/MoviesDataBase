package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.services.MovieService;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Aj's | Main")
@Route(value = "", layout = MainLayout.class)
@CssImport(value = "/themes/styles.css")
@AnonymousAllowed
public class MainPage extends VerticalLayout {

    Grid<AppUser> grid = new Grid<>(AppUser.class, false);
    Grid<Movie> movieGrid = new Grid<>(Movie.class, false);
    UserService userService;
    MovieService movieService;

    public MainPage (UserService userService, MovieService movieService) {
        addClassName("main-page");
        this.movieService = movieService;
        this.userService = userService;

        setAlignItems(Alignment.CENTER);
        setPadding(true);
        setSizeFull();

        H1 titlePage = new H1("Review thy movies");

        add(titlePage);

        Div main = new Div(movieGrid, grid);


        HorizontalLayout users = new HorizontalLayout(grid);
        users.setSizeFull();

        grid.setItems(userService.findAll());
        grid.setWidth(50, Unit.PERCENTAGE);
        grid.addClassName("user-grid");

        grid.addColumn(AppUser::getUserName).setHeader("User");
        grid.addColumn(AppUser::getEmail).setHeader("Email");
        grid.addColumn(AppUser::getAge).setHeader("Age");

        HorizontalLayout movies = new HorizontalLayout(movieGrid);
        movies.setSizeFull();

        movieGrid.setItems(movieService.findAll());

        movieGrid.addColumn(Movie::getTitle).setHeader("Title");
        movieGrid.addColumn(Movie::getDirector).setHeader("Director");
        movieGrid.addColumn(Movie::getReleaseYear).setHeader("Released");
        movieGrid.addColumn(Movie::getGenre).setHeader("Genre");


        add(users, movies, main);

    }


    RouterLink userPageLink = new RouterLink("Users", UserPage.class);
    VerticalLayout sideDrawer = new VerticalLayout(userPageLink);

    RouterLink moviePageLink = new RouterLink("Movies", MoviePage.class);


    public void updateUsers() {
        grid.setItems((userService.findAll()));
    }

    public void updateMovies() {movieGrid.setItems((movieService.findAll()));}
}
