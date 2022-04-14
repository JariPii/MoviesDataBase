package com.example.moviesdatabase.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Div {

    LoginOverlay loginOverlay = new LoginOverlay();

    public LoginView() {
        loginOverlay.setTitle("Movie reviews");
        loginOverlay.setDescription("Give a review");
        loginOverlay.setOpened(true);
        loginOverlay.setAction("login");

        add(loginOverlay);
    }

}
