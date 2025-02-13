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

    public String registerUser(user user) {
        // Verifica se o email já existe no banco
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Erro: Email já cadastrado.";
        }

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Define o username baseado no email
        user.setUsername(user.getEmail());

        // Salva o usuário no banco
        userRepository.save(user);

        return "Usuário cadastrado com sucesso!";
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
