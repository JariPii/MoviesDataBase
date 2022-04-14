package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.LoginForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    LoginOverlay loginOverlay = new LoginOverlay();

    public LoginView() {
        loginOverlay.setTitle("Movie reviews");
        loginOverlay.setDescription("Give a review");
        loginOverlay.setOpened(true);
        loginOverlay.setAction("login");

        add(loginOverlay);
    }

}
