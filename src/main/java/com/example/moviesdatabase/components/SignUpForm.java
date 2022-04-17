package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;

@CssImport(value = "/themes/styles.css",
themeFor = "vaadin-text-field")
public class SignUpForm extends FormLayout {

    TextField userName = new TextField("Name");
    EmailField email = new EmailField("Email");
    PasswordField password = new PasswordField("Set password");
    PasswordField password2 = new PasswordField("Confirm password");
    DatePicker datePicker = new DatePicker("Birthday");
    Button signUser = new Button("Sign Up");

    Binder<AppUser> binder = new BeanValidationBinder<>(AppUser.class);
    UserService userService;

    public SignUpForm(UserService userServices) {
        addClassName("signup-form");
        this.userService = userServices;
        binder.bindInstanceFields(this);
        setVisible(false);

        FormLayout formLayout = new FormLayout();
        formLayout.add(userName, email, password, password2, datePicker);

        userName.setRequiredIndicatorVisible(true);
        userName.setErrorMessage("Required");

        formLayout.setResponsiveSteps(
                new ResponsiveStep("0" , 1),
                new ResponsiveStep("500px", 2)
        );

        formLayout.setColspan(datePicker, 1);
        formLayout.setColspan(password, 1);

        userName.setClearButtonVisible(true);

        signUser.addClickListener(e -> saveUser());

        add(userName, email, datePicker, password,password2, signUser);

    }


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

        this.getParent().ifPresent(c -> {
            if (c instanceof Dialog) {
                ((Dialog) c).close();
            }
        });
    }

    public void setUser(AppUser user) {

        if(user != null) {
            binder.setBean(user);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

}










