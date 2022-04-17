package com.example.moviesdatabase.services;

import com.example.moviesdatabase.entities.AppUser;
import com.example.moviesdatabase.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    AppUserRepository userRepository;

    public UserService(AppUserRepository userRepository) {this.userRepository = userRepository;}

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser save(AppUser user){
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public AppUser updateById(int id, AppUser newUser) {
        AppUser oldUser = userRepository.findById(id).orElseThrow();

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
