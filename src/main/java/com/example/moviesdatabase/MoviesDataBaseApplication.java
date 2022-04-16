package com.example.moviesdatabase;

import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.ReviewRepository;
import com.example.moviesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class MoviesDataBaseApplication {

    /*@Autowired
    PasswordEncoder passwordEncoder;*/

    public static void main(String[] args) {
        SpringApplication.run(MoviesDataBaseApplication.class, args);
    }

    @Bean
    CommandLineRunner init (
            UserRepository userRepository,
            MovieRepository movieRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {
            User todo = new User("Jari",("pass"), "kas@com", LocalDate.of(1975, Month.OCTOBER, 10));
            User todo2 = new User("Jari2",("pass2"), "kas@com", LocalDate.of(1975, Month.OCTOBER, 10));


            /*User todo1 = new User("Neeeeej",
                    "Jojojo");*/

            userRepository.saveAll(List.of(
                    todo, todo2
            ));

            Movie star = new Movie("Star Wars", "George Lucas", 1977, "Sciencefiction");
            Movie star2 = new Movie("Star Wars 2", "George Lucas", 1980, "Sciencefiction");
            Movie star3 = new Movie("Star Wars 2", "George Lucas", 1980, "Sciencefiction");

            movieRepository.saveAll(List.of(
                    star, star2, star3
            ));

            Review first = new Review("Star Wars", "Good shit, lots of pewpew," +
                    "no titties, flying spaceship, one dude with breethin problems, a gay robot", todo, star);
            Review second = new Review("Star Wars", "Good shit, lots of pewpew," +
                    "no titties, flying spaceship, one dude with breethin problems, a gay robot", todo, star2);
            Review third = new Review("Star Wars", "Good shit, lots of pewpew," +
                    "no titties, flying spaceship, one dude with breethin problems, a gay robot too", todo2, star3);
            Review fourth = new Review("Star Trek", "Good shit, lots of pewpew," +
                    "no titties, flying spaceship, one dude with breethin problems, a gay robot too", todo2, star3);

            reviewRepository.saveAll(List.of(
                    first, second, third, fourth
            ));

        };


    }



}
