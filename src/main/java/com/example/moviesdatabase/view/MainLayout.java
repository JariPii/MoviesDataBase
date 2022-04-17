package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.security.PrincipalUtil;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    UserService userService;

    public MainLayout(UserService userService) {

        this.userService = userService;

        HorizontalLayout navbarLayout = new HorizontalLayout();

        // SKA VARA KVAR
        navbarLayout.add(new DrawerToggle(), new H1("Review"), new H2("Logged in as: " + PrincipalUtil.getPrincipalName()));



        Button loginButton = new Button("Login", e -> {
            UI.getCurrent().navigate(LoginView.class);
            UI.getCurrent().navigate("");
        });

        Button logoutButton = new Button("Logout", e -> PrincipalUtil.logout());

        if(PrincipalUtil.isLoggedIn()) {
            navbarLayout.add(logoutButton);
        } else {
            navbarLayout.add(loginButton);
        }


        Button signUpButton = new Button("Sign up", e -> {
            Dialog dialog = new Dialog();
            SignUpForm dialogForm = new SignUpForm(userService);
            dialogForm.setUser(new AppUser());
            AppUser user = new AppUser();

            dialogForm.setUser(user);

            dialog.add(dialogForm);
            dialog.open();
        });

        addToNavbar(navbarLayout);

        //SKA VARA KVAR
        navbarLayout.setWidthFull();
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        navbarLayout.setMargin(true);

        addToNavbar(navbarLayout, signUpButton);

        VerticalLayout sideDrawer = new VerticalLayout();
        sideDrawer.addClassName("sideDrawer");

        RouterLink userPageLink = new RouterLink("Users", UserPage.class);
        RouterLink mainPageLink = new RouterLink("Main Page", MainPage.class);
        RouterLink moviesPageLink = new RouterLink("Movies", MoviePage.class);
        RouterLink reviewsPageLink = new RouterLink("Reviews", ReviewPage.class);

        RouterLink reviewLink = new RouterLink("Edit reviews", ReviewView.class);
        if(PrincipalUtil.isLoggedIn())
            sideDrawer.add(reviewLink);

        addToDrawer(sideDrawer);

        sideDrawer.add(mainPageLink, userPageLink, reviewsPageLink, moviesPageLink);

    }

}
