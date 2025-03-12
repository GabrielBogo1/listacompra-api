package com.listadecompras.listacompras.controller;

import com.listadecompras.listacompras.dto.AuthenticationDTO;
import com.listadecompras.listacompras.dto.LoginResponseDTO;
import com.listadecompras.listacompras.dto.RegisterDTO;
import com.listadecompras.listacompras.repository.UserRepository;
import com.listadecompras.listacompras.service.AuthorizationService;
import com.listadecompras.listacompras.service.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listadecompras.listacompras.entity.User;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
    @Autowired
    private UserRepository userRepository;
    
    //@Autowired
    //private AuthorizationService authorizationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CREDENCIAIS INVALIDAS");
    }
    }

    @PostMapping ("/register")
    public ResponseEntity register (@RequestBody RegisterDTO registerDTO){
       if (this.userRepository.findByEmail(registerDTO.email())!= null) return ResponseEntity.badRequest().build();

       String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
       User newUser = new User(registerDTO.username(), registerDTO.email(), encryptedPassword, registerDTO.role());

       this.userRepository.save(newUser);

       return ResponseEntity.ok().build();
    }


    // @PostMapping("/login")
    // public ResponseEntity<String> login(@RequestBody user loginRequest) {
    // String response = userService.login(user.getEmail(), user.getPassword());
    // return ResponseEntity.ok(response);
    // }

}
