package com.listadecompras.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listadecompras.listacompras.entity.user;
import com.listadecompras.listacompras.service.userService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class userController {
     
    @Autowired
    userService userService;
    
     @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody user user) {
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }
}
