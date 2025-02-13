package com.listadecompras.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listadecompras.listacompras.entity.user;
import com.listadecompras.listacompras.service.userService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class loginController {

    @Autowired
    private userService userService;

    @PostMapping("/login")
    public ResponseEntity<user> login(@RequestBody user user) {
        user loggedInUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    // @PostMapping("/login")
    // public ResponseEntity<String> login(@RequestBody user loginRequest) {
    // String response = userService.login(user.getEmail(), user.getPassword());
    // return ResponseEntity.ok(response);
    // }

}
