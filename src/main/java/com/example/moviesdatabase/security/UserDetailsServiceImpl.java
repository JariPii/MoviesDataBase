package com.example.moviesdatabase.security;


import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findAppUserByUserName(userName).orElseThrow();

        return new User(appUser.getUserName(), appUser.getPassword(), List.of());
    }
}
