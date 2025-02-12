package com.listadecompras.listacompras.entity;

import org.hibernate.annotations.processing.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity 
@Table (name= "users")
public class user {
    

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    @Column(name = "username", nullable = false, length = 250)
    private String username;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column (name = "password", nullable = false, length = 150)
    private String password;

}
