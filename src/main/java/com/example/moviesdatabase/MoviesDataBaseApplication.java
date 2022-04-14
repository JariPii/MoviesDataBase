package com.example.moviesdatabase;

import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class MoviesDataBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesDataBaseApplication.class, args);
    }

    @Bean
    CommandLineRunner init (
            UserRepository userRepository
    ) {
        return args -> {
            User todo = new User("Jari","pass", "kas@com", LocalDate.of(1975, Month.OCTOBER, 10));

            /*User todo1 = new User("Neeeeej",
                    "Jojojo");*/

            userRepository.saveAll(List.of(
                    todo //todo1
            ));

        };


    }

}
