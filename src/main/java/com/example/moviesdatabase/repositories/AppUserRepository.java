package com.example.moviesdatabase.repositories;

import com.example.moviesdatabase.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Integer> {

   //User findUserByUserName(String username);

   Optional<AppUser> findAppUserByUserName(final String userName);
}
