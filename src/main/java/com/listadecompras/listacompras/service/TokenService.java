package com.listadecompras.listacompras.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.listadecompras.listacompras.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("product-api")
                    .withSubject(user.getEmail())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .withExpiresAt(genExperationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating Token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("product-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            System.out.println("Erro ao validar token: " + exception.getMessage());
            return null;
        }
    }

    private Instant genExperationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
