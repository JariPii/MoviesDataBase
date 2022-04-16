package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.services.MovieService;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.stereotype.Component;

@PageTitle("Aj's | Main")
@Route(value = "main", layout = MainLayout.class)
@CssImport(value = "/themes/styles.css")
@AnonymousAllowed
public class MainPage extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class, false);
    Grid<Movie> movieGrid = new Grid<>(Movie.class, false);
    UserService userService;
    SignUpForm signUpForm;
    MovieService movieService;
    /*UserPage userPage;*/

    public MainPage (UserService userService/*, UserPage userPage*/, MovieService movieService) {
        addClassName("main-page");
        this.movieService = movieService;
        this.userService = userService;
        /*this.userPage = userPage;*/
        /*this.signUpForm = new SignUpForm(userService, this);*/

        setAlignItems(Alignment.CENTER);
        setPadding(true);
        setSizeFull();

        H1 titlePage = new H1("Review thy movies");

        add(titlePage);

        Div main = new Div(movieGrid, grid);

        grid.addComponentColumn(user -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE), e -> {
                Notification.show(user.getUserName());
                userService.deleteById(user.getId());
                updateUsers();
            });

            return button;
        });



        //VerticalLayout main = new VerticalLayout(grid);
        HorizontalLayout users = new HorizontalLayout(grid);
        users.setSizeFull();

        grid.setItems(userService.findAll());
        grid.setWidth(50, Unit.PERCENTAGE);
        grid.addClassName("user-grid");



        /*Button button = new Button("Add new user", e -> {
            Dialog dialog = new Dialog();
            SignUpForm dialogForm = new SignUpForm(userService, this);
            dialogForm.setUser(new User());
            User user = new User();

            dialogForm.setUser(user);

            dialog.add(dialogForm);
            dialog.open();
        });*/

        grid.addColumn(User::getUserName).setHeader("User");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(User::getAge).setHeader("Age");

        HorizontalLayout movies = new HorizontalLayout(movieGrid);
        movies.setSizeFull();

        movieGrid.setItems(movieService.findAll());

        movieGrid.addColumn(Movie::getTitle).setHeader("Title");
        movieGrid.addColumn(Movie::getDirector).setHeader("Director");
        movieGrid.addColumn(Movie::getReleaseYear).setHeader("Released");
        movieGrid.addColumn(Movie::getGenre).setHeader("Genre");





        add(users/*, button*/, movies, main);




    }



    RouterLink userPageLink = new RouterLink("Users", UserPage.class);
    VerticalLayout sideDrawer = new VerticalLayout(userPageLink);

    RouterLink moviePageLink = new RouterLink("Movies", MoviePage.class);


    public void updateUsers() {
        grid.setItems((userService.findAll()));
    }

    public void updateMovies() {movieGrid.setItems((movieService.findAll()));}
}
