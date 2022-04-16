package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Aj's | Users")
@Route(value = "/users", layout = MainLayout.class)
@AnonymousAllowed
public class UserPage extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class, false);
    UserService userService;
    UserRepository userRepository;
    SignUpForm signUpForm;

    public UserPage(UserService userService) {
        //ddClassName("user-view");
        this.userService = userService;


        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Faaaan");

        grid.setItems(userService.findAll());
        grid.setWidthFull();

        HorizontalLayout main = new HorizontalLayout(grid, title);
        main.setSizeFull();

        grid.addColumn(User::getUserName).setHeader("Username");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(User::getAge).setHeader("Age");

        add(main);

    }

    public void updateUsers() {
        grid.setItems((userService.findAll()));
    }
}

