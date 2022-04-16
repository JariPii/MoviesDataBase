package com.example.moviesdatabase.services;

import com.example.moviesdatabase.entities.User;
import com.example.moviesdatabase.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User updateById(int id, User newUser) {
        User oldUser = userRepository.findById(id).orElseThrow();

        if(newUser.getUserName() != null)
            oldUser.setUserName(newUser.getUserName());
        if(newUser.getEmail() != null)
            oldUser.setEmail(newUser.getEmail());
        if(newUser.getDateOfBirth() != null)
            oldUser.setDateOfBirth(newUser.getDateOfBirth());


        userRepository.save(newUser);
        return oldUser;

    }

/*public Optional<User> findByUserName(String principalName) {
        return userRepository.findByUserNameList(principalName);
}*/




}
