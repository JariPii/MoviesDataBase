package com.example.moviesdatabase.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class PrincipalUtil {


    /*public User getLoggedIn() {
        return userRepository.findByUserName(getLoggedIn().getUserName());
    }*/

    public static String getPrincipalName() {

        return SecurityContextHolder.getContext().getAuthentication().getName();

    }

    public static boolean isLoggedIn(){
        return !SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName().equalsIgnoreCase("anonymousUser");
}

    public static void logout(){

        UI.getCurrent().navigate("main");
        new SecurityContextLogoutHandler()
        .logout(VaadinServletRequest.getCurrent()
                .getHttpServletRequest(),null, null);

    }

}
