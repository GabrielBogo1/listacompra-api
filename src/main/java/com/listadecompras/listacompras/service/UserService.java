package com.listadecompras.listacompras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.listadecompras.listacompras.entity.User;
import com.listadecompras.listacompras.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordService passwordService;

      private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Erro: Email já cadastrado.");
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        user.setUsername(user.getEmail());
    
        return userRepository.save(user);
    }    

    public User login(String email, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            if (passwordService.matches(rawPassword, user.getPassword())) {
                return user; 
            }
        }
    
        return null; 
    }    
}
