package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.services.UserService;
import com.example.moviesdatabase.view.MainPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.context.annotation.ComponentScans;

import java.time.LocalDate;

@CssImport(value = "/themes/styles.css",
themeFor = "vaadin-text-field")
public class SignUpForm extends FormLayout {

    TextField userName = new TextField("Name");
    //TextField email = new TextField("Email");
    EmailField email = new EmailField();
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

        FormLayout formLayout = new FormLayout();
        formLayout.add(userName, email, password, datePicker);

        /*email.setLabel("Email address");
        email.getElement().setAttribute("name", "email");
        //validEmail.setPlaceholder(validEmail.getPattern());
        email.setErrorMessage("Please enter a valid example.com email address");
        email.setClearButtonVisible(true);
        email.setPattern("^.+@\\.com$");*/
        //add(email);

        userName.setRequiredIndicatorVisible(true);
        userName.setErrorMessage("Required");

        formLayout.setResponsiveSteps(
                new ResponsiveStep("0" , 1),
                new ResponsiveStep("500px", 2)
        );

        formLayout.setColspan(datePicker, 1);
        formLayout.setColspan(password, 2);



        userName.setClearButtonVisible(true);





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










