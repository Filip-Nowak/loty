package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public void addUser(User user){
      userRepository.save(user);
    }
    public User getUserById(long id){
         Optional<User>optionalUser= userRepository.findById(id);
         if(optionalUser.isEmpty())
             return null;
         return optionalUser.get();
    }

}
