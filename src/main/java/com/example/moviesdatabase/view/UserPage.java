package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.repositories.AppUserRepository;
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

    Grid<AppUser> grid = new Grid<>(AppUser.class, false);
    UserService userService;
    AppUserRepository userRepository;
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

        grid.addColumn(AppUser::getUserName).setHeader("Username");
        grid.addColumn(AppUser::getEmail).setHeader("Email");
        grid.addColumn(AppUser::getAge).setHeader("Age");

        add(main);

    }

    public void updateUsers() {
        grid.setItems((userService.findAll()));
    }
}

