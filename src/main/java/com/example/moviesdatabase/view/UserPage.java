package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/users", layout = MainLayout.class)
public class UserPage extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class, false);
    UserService userService;
    /*MainPage mainPage;*/
    //UserPage userPage;
    UserRepository userRepository;
    SignUpForm signUpForm;

    public UserPage(UserService userService) {
        /*this.mainPage = mainPage;*/
        this.userService = userService;
        /*this.userPage = userPage;*/
        this.userRepository = userRepository;
        this.signUpForm = signUpForm;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 title = new H1("Faaaan");

        userService.findAll();

        add(title);

    }


}
