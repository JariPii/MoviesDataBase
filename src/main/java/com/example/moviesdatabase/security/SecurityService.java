/*
package com.example.moviesdatabase.security;

import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import com.example.moviesdatabase.view.LoginView;
import com.example.moviesdatabase.view.MainPage;
import com.example.moviesdatabase.view.ReviewPage;
import com.example.moviesdatabase.view.UserPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean LoginUser(String username, String password) {
        User user = userRepository.findUserByUserName(username);

        return (user != null && user.getPassword().equals(password));

    }
}
*/
