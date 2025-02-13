package com.listadecompras.listacompras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.listadecompras.listacompras.entity.user;
import com.listadecompras.listacompras.repository.userRepository;


@Service
public class userService {

    @Autowired
    userRepository userRepository;

    @Autowired
    passwordService passwordService;

      private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      public user registerUser(user user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Erro: Email já cadastrado.");
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        user.setUsername(user.getEmail());
    
        return userRepository.save(user);
    }    

    public user login(String email, String rawPassword) {
        Optional<user> optionalUser = userRepository.findByEmail(email);
    
        if (optionalUser.isPresent()) {
            user user = optionalUser.get(); 
            
            if (passwordService.matches(rawPassword, user.getPassword())) {
                return user; 
            }
        }
    
        return null; 
    }    
}
