package com.listadecompras.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listadecompras.listacompras.entity.User;
import com.listadecompras.listacompras.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }


    // @PostMapping("/login")
    // public ResponseEntity<String> login(@RequestBody user loginRequest) {
    // String response = userService.login(user.getEmail(), user.getPassword());
    // return ResponseEntity.ok(response);
    // }

}
