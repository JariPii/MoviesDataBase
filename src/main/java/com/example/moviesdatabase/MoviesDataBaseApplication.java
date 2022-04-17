package com.example.moviesdatabase;

import com.example.moviesdatabase.entities.Movie;
import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.repositories.MovieRepository;
import com.example.moviesdatabase.repositories.ReviewRepository;
import com.example.moviesdatabase.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            AppUserRepository userRepository,
            MovieRepository movieRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {
            AppUser todo = new AppUser("Jari",("pass"), "kas@com", LocalDate.of(1975, Month.OCTOBER, 10));
            AppUser todo2 = new AppUser("Gunnar",("pass"), "2kas@com", LocalDate.of(1975, Month.OCTOBER, 10));


            /*User todo1 = new User("Neeeeej",
                    "Jojojo");*/

            userRepository.saveAll(List.of(
                    todo, todo2
            ));

            Movie star = new Movie("Star Wars", "George Lucas", 1977, "Sciencefiction");
            Movie star2 = new Movie("Star Wars Empire", "George Lucas", 1980, "Sciencefiction");
            Movie star3 = new Movie("Star Wars Jedi", "George Lucas", 1980, "Sciencefiction");
            Movie shark = new Movie("Jaws", "Steven Spielberg", 1980, "Dokumentär");
            Movie rCop = new Movie("Robocop", "Paul Verhoeven", 1987, "Robot i burk");
            Movie sex = new Movie("Deep throat", "George Lucas", 1979, "Mat och dryck");
            Movie f1 = new Movie("Rush", "Ron Howard", 2013, "Action");

            movieRepository.saveAll(List.of(
                    star, star2, star3, shark, rCop, sex, f1
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
