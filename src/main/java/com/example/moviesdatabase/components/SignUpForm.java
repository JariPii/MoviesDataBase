package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.services.UserService;
import com.example.moviesdatabase.view.MainPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;


public class SignUpForm extends FormLayout {

    TextField userName = new TextField("Name");
    TextField email = new TextField("Email");
    TextField password = new TextField("Set password");
    DatePicker datePicker = new DatePicker("Birthday");
    Button signUser = new Button("Sign Up");

    Binder<User> binder = new BeanValidationBinder<>(User.class);
    UserService userService;
    MainPage mainPage;


    public SignUpForm(UserService userServices/*, MainPage mainPage*/) {
        addClassName("signup-form");
        this.userService = userServices;
        /*this.mainPage = mainPage;*/
        binder.bindInstanceFields(this);
        setVisible(false);

        signUser.addClickListener(e -> saveUser());

        add(userName, email, datePicker, password, signUser /*createButton()*/);

    }

    /*private Component createButton() {
        signUser.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(signUser);
    }*/


    private void saveUser() {
        User user = binder.validate().getBinder().getBean();
        LocalDate userBDay = LocalDate.of(datePicker.getValue().getYear(), datePicker.getValue().getMonth(), datePicker.getValue().getDayOfMonth());
        user.setDateOfBirth(userBDay);

        if(user.getId() == 0) {
            userService.save(user);
        } else {
            userService.updateById(user.getId(), user);
        }

        setUser(null);



        /*mainPage.updateUsers()*/;

        this.getParent().ifPresent(c -> {
            if (c instanceof Dialog) {
                ((Dialog) c).close();
            }
        });
    }

        public void setUser(User user) {

            if(user != null) {
                binder.setBean(user);
                setVisible(true);
            } else {
                setVisible(false);
            }
        }


    }










