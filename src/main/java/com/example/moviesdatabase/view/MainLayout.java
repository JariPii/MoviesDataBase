package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    UserService userService;
    /*MainPage mainPage;*/

    public MainLayout(UserService userService /*MainPage mainPage*/) {

        this.userService = userService;
        /*this.mainPage = mainPage;*/

        HorizontalLayout navbarLayout = new HorizontalLayout();

        navbarLayout.add(new DrawerToggle(), new H1("Review"));

        Button loginButton = new Button("Login", e -> {
            UI.getCurrent().navigate(LoginView.class);
        });

        /*Button button = new Button("Sign up");*/

        Button button = new Button("Add new user", e -> {
            Dialog dialog = new Dialog();
            SignUpForm dialogForm = new SignUpForm(userService);
            dialogForm.setUser(new User());
            User user = new User();

            dialogForm.setUser(user);

            dialog.add(dialogForm);
            dialog.open();
        });

        navbarLayout.setWidthFull();
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setMargin(true);

        addToNavbar(navbarLayout, loginButton, button);

        RouterLink userPageLink = new RouterLink("Users", UserPage.class);
        VerticalLayout sideDrawer = new VerticalLayout(userPageLink);

        RouterLink mainPageLink = new RouterLink("Main Page", MainPage.class);
        sideDrawer.add(mainPageLink);

        addToDrawer(sideDrawer);
        //sideDrawer.add(userPageLink, mainPageLink);

    }

}
