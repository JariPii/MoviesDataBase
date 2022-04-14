package com.example.moviesdatabase.view;

import com.example.moviesdatabase.components.SignUpForm;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "/", layout = MainLayout.class)
public class MainPage extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class, false);
    UserService userService;
    SignUpForm signUpForm;
    /*UserPage userPage;*/

    public MainPage(UserService userService/*, UserPage userPage*/) {
        this.userService = userService;
        /*this.userPage = userPage;*/
        /*this.signUpForm = new SignUpForm(userService, this);*/

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 titlePage = new H1("FAAAAAAAN");

        grid.setItems(userService.findAll());
        grid.setWidthFull();

        grid.addComponentColumn(user -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE), e -> {
                Notification.show(user.getUserName());
                userService.deleteById(user.getId());
                updateUsers();
            });

            return button;
        });

        HorizontalLayout main = new HorizontalLayout(grid, titlePage);
        main.setSizeFull();

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



        add(main/*, button*/);


    }

    RouterLink userPageLink = new RouterLink("Users", UserPage.class);
    VerticalLayout sideDrawer = new VerticalLayout(userPageLink);



    public void updateUsers() {
        grid.setItems((userService.findAll()));
    }
}
